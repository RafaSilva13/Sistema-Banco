package com.mycompany.models.ClassesAuxiliares;

import java.awt.Component;
import java.text.NumberFormat;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rafael Pereira da Silva Matricula: 202235013
 */

public class CurrencyRenderer extends DefaultTableCellRenderer {
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    public CurrencyRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT); // Alinha o texto à direita
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Number) {
            value = currencyFormat.format(value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}