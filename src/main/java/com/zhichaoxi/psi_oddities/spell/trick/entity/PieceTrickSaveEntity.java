package com.zhichaoxi.psi_oddities.spell.trick.entity;

import com.zhichaoxi.psi_oddities.component.ModComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.entity.PartEntity;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.piece.PieceTrick;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PieceTrickSaveEntity extends PieceTrick {

    private static final UUID EMPTY_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    SpellParam<Number> number;
    SpellParam<Entity> target;

    public PieceTrickSaveEntity(Spell spell) {
        super(spell);
        setStatLabel(EnumSpellStat.COMPLEXITY, new StatLabel(2));
        setStatLabel(EnumSpellStat.POTENCY, new StatLabel(SpellParam.GENERIC_NAME_NUMBER, true).mul(8));
    }

    @Override
    public void initParams() {
        addParam(number = new ParamNumber(SpellParam.GENERIC_NAME_NUMBER, SpellParam.BLUE, false, true));
        addParam(target = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);
        meta.addStat(EnumSpellStat.COMPLEXITY, 1);

        Double numberVal = this.<Double>getParamEvaluation(number);
        if(numberVal == null || numberVal <= 0 || numberVal != numberVal.intValue()) {
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_INTEGER, x, y);
        }
        meta.addStat(EnumSpellStat.POTENCY, numberVal.intValue() * 8);
    }

    public static List<UUID> getUUIDList(ItemStack cadStack) {
        return cadStack.getOrDefault(ModComponents.STORED_ENTITY, new ArrayList<>());
    }

    public static List<UUID> setUUID(List<UUID> list, int index, UUID uuid) {
        while (list.size() <= index) {
            list.add(EMPTY_UUID);
        }
        list.set(index, uuid);
        return list;
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Entity targetVal = this.getParamValue(context, target);
        int numberVal = this.getParamValue(context, number).intValue();
        int n = numberVal - 1;
        ItemStack cadStack = PsiAPI.getPlayerCAD(context.caster);
        if(cadStack == null || !(cadStack.getItem() instanceof ICAD cad)) {
            throw new SpellRuntimeException(SpellRuntimeException.NO_CAD);
        }

        if (n >= cad.getMemorySize(cadStack)) {
            throw new SpellRuntimeException(SpellRuntimeException.MEMORY_OUT_OF_BOUNDS);
        }

        context.verifyEntity(targetVal);
        if (!context.isInRadius(targetVal)) {
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
        }

        List<UUID> list = new ArrayList<>(getUUIDList(cadStack));
        if (targetVal instanceof PartEntity<?> partEntity) {
            Entity parent = partEntity.getParent();
            cadStack.set(ModComponents.STORED_ENTITY,
                    setUUID(list, n, parent.getUUID()));
        } else {
            cadStack.set(ModComponents.STORED_ENTITY,
                    setUUID(list, n, targetVal.getUUID()));
        }

        return null;
    }
}

