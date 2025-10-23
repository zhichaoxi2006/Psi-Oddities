/*
 * This class is distributed as part of the Psi Mod.
 * Get the Source Code in GitHub:
 * https://github.com/Vazkii/Psi
 *
 * Psi is Open Source and distributed under the
 * Psi License: https://psi.vazkii.net/license.php
 */
package com.zhichaoxi.psi_oddities.spell.trick;

import com.zhichaoxi.psi_oddities.spell.base.SpellParams;
import com.zhichaoxi.psi_oddities.spell.param.ParamString;
import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickPlayMusic extends PieceTrick {

	SpellParam<Vector3> position;
	SpellParam<Number> pitch;
	SpellParam<Number> volume;
	SpellParam<String> id;

	public PieceTrickPlayMusic(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false));
		addParam(id = new ParamString(SpellParams.GENERIC_NAME_ID, SpellParam.RED, false, false));
		addParam(pitch = new ParamNumber(SpellParam.GENERIC_NAME_PITCH, SpellParam.GREEN, true, false));
		addParam(volume = new ParamNumber(SpellParam.GENERIC_NAME_VOLUME, SpellParam.YELLOW, true, false));
	}

	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		double dVol = SpellHelpers.ensurePositiveOrZero(this, volume, 1);
		double dPit = SpellHelpers.ensurePositiveOrZero(this, pitch, 0);

		if(dPit > 24) {
			throw new SpellCompilationException(SpellCompilationException.PITCH, x, y);
		}

		if(dVol > 1) {
			throw new SpellCompilationException(SpellCompilationException.VOLUME, x, y);
		}
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		BlockPos pos = SpellHelpers.getBlockPos(this, context, position, true, false);
		String idVal = this.getParamValue(context, id);
		double volVal = this.getParamValueOrDefault(context, volume, 1).doubleValue();
		double pitchVal = this.getParamValueOrDefault(context, pitch, 0).doubleValue();

        ResourceLocation location = ResourceLocation.parse(idVal);
        SoundEvent soundEvent = BuiltInRegistries.SOUND_EVENT.get(location);
        if (soundEvent == null) {
            throw new SpellRuntimeException(ErrorUtil.INVAILD_SOUND_EVENT);
        }

        float f = (float) Math.pow(2, (pitchVal - 12) / 12.0);

		context.focalPoint.level().playSound(null, pos, soundEvent, SoundSource.RECORDS, (float) volVal, f);
		return null;
	}
}
