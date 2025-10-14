package com.zhichaoxi.psi_oddities.spell.operator.string;

import com.zhichaoxi.psi_oddities.util.ErrorUtil;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.piece.PieceOperator;

import java.util.ArrayList;
import java.util.List;

public class PieceOperatorGetCommentVector extends PieceOperator {

    public PieceOperatorGetCommentVector(Spell spell) {
        super(spell);
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        List<Double> list = parseString(comment);
        if (this.comment == null || this.comment.isEmpty()) {
            throw new SpellCompilationException(ErrorUtil.NAN_COMMENT, x, y);
        } else if (list == null) {
            throw new SpellCompilationException(ErrorUtil.NULL_VECTOR);
        }
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        List<Double> list = parseString(comment);
        return new Vector3(list.get(0), list.get(1), list.get(2));
    }

    public static List<Double> parseString(String input) {
        String regex = "^\\[\\s*(-|\\+)?\\d+(\\.\\d+)?\\s*,\\s*(-|\\+)?\\d+(\\.\\d+)?\\s*,\\s*(-|\\+)?\\d+(\\.\\d+)?\\s*\\]$";

        if (input == null || !input.matches(regex)) {
            return null;
        }

        String content = input.substring(1, input.length() - 1);

        String[] numberStrs = content.split("\\s*,\\s*");

        if (numberStrs.length != 3) {
            return null;
        }

        List<Double> numbers = new ArrayList<>(3);
        try {
            numbers.add(Double.parseDouble(numberStrs[0].trim()));
            numbers.add(Double.parseDouble(numberStrs[1].trim()));
            numbers.add(Double.parseDouble(numberStrs[2].trim()));
        } catch (NumberFormatException e) {
            return null;
        }

        return numbers;
    }

    @Override
    public Class<?> getEvaluationType() {
        return Vector3.class;
    }
}
