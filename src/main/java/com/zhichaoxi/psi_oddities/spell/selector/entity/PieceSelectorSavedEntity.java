package com.zhichaoxi.psi_oddities.spell.selector.entity;

import com.zhichaoxi.psi_oddities.component.ModComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceSelector;

import java.util.List;
import java.util.UUID;

public class PieceSelectorSavedEntity extends PieceSelector {

    SpellParam<Number> number;

    public PieceSelectorSavedEntity(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).mul(6));
    }

    @Override
    public void initParams() {
        addParam(number = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.BLUE, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        Double numberVal = this.<Double>getParamEvaluation(number);
        if(numberVal == null || numberVal <= 0 || numberVal != numberVal.intValue()) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_INTEGER, x, y);
        }

        meta.addStat(EnumSpellStat.POTENCY, numberVal.intValue() * 6);
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        int numberVal = this.getParamValue(context, number).intValue();

        int n = numberVal - 1;
        Player caster = context.caster;

        ItemStack cadStack = PsiAPI.getPlayerCAD(caster);
        if(cadStack == null || !(cadStack.getItem() instanceof ICAD cad)) {
            throw new SpellRuntimeException(SpellRuntimeException.NO_CAD);
        }

        List<UUID> list = cadStack.get(ModComponents.STORED_ENTITY);
        if (list != null) {
            UUID uuid = list.get(n);
            ServerLevel level = (ServerLevel) caster.level();
            Entity entity = level.getEntity(uuid);
            if (entity == null) {
                throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
            }
            return entity;
        }

        throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
    }

    @Override
    public Class<?> getEvaluationType() {
        return Entity.class;
    }

}
