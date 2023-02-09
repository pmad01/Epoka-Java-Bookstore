package application.bookstore.auxiliaries;

import java.util.*;

public class TableGenerator {

    // helpers
    private int PADDING_SIZE = 2;
    private String NEW_LINE = "\n";
    private String TABLE_JOINT_SYMBOL = "+";
    private String TABLE_V_SPLIT_SYMBOL = "|";
    private String TABLE_H_SPLIT_SYMBOL = "-";

    public String generateTable(List<String> headersList, List<List<String>> rowsList,int... headerHeight)
    {
        StringBuilder _str_ = new StringBuilder();

        int rowHeight = headerHeight.length > 0 ? headerHeight[0] : 1;

        Map<Integer,Integer> columnMaxWidthMapping = getMaxWidthOfTable(headersList, rowsList);

        _str_.append(NEW_LINE);
        _str_.append(NEW_LINE);
        createRowLine(_str_, headersList.size(), columnMaxWidthMapping);
        _str_.append(NEW_LINE);


        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(_str_, headersList.get(headerIndex), headerIndex, columnMaxWidthMapping);
        }

        _str_.append(NEW_LINE);

        createRowLine(_str_, headersList.size(), columnMaxWidthMapping);


        for (List<String> row : rowsList) {

            for (int i = 0; i < rowHeight; i++) {
                _str_.append(NEW_LINE);
            }

            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(_str_, row.get(cellIndex), cellIndex, columnMaxWidthMapping);
            }

        }

        _str_.append(NEW_LINE);
        createRowLine(_str_, headersList.size(), columnMaxWidthMapping);
        _str_.append(NEW_LINE);
        _str_.append(NEW_LINE);

        return _str_.toString();
    }

    private void fillSpace(StringBuilder stringBuilder, int length)
    {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }

    private void createRowLine(StringBuilder stringBuilder,int headersListSize, Map<Integer,Integer> columnMaxWidthMapping)
    {
        for (int i = 0; i < headersListSize; i++) {
            if(i == 0)
            {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }

            for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2 ; j++) {
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }


    private Map<Integer,Integer> getMaxWidthOfTable(List<String> headersList, List<List<String>> rowsList)
    {
        Map<Integer,Integer> colMaxWidth = new HashMap<>();

        for (int col = 0; col < headersList.size(); col++) {
            colMaxWidth.put(col, 0);
        }

        for (int col = 0; col < headersList.size(); col++) {

            if(headersList.get(col).length() > colMaxWidth.get(col))
            {
                colMaxWidth.put(col, headersList.get(col).length());
            }
        }


        for (List<String> row : rowsList) {

            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {

                if(row.get(columnIndex).length() > colMaxWidth.get(columnIndex))
                {
                    colMaxWidth.put(columnIndex, row.get(columnIndex).length());
                }
            }
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if(colMaxWidth.get(columnIndex) % 2 != 0)
            {
                colMaxWidth.put(columnIndex, colMaxWidth.get(columnIndex) + 1);
            }
        }


        return colMaxWidth;
    }

    private int getOptimumCellPadding(int cellIndex,int datalength,Map<Integer,Integer> columnMaxWidthMapping,int cellPaddingSize)
    {
        if(datalength % 2 != 0)
        {
            datalength++;
        }

        if(datalength < columnMaxWidthMapping.get(cellIndex))
        {
            cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
        }

        return cellPaddingSize;
    }

    private void fillCell(StringBuilder stringBuilder, String cell, int cellIndex, Map<Integer,Integer> columnMaxWidthMapping)
    {

        int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), columnMaxWidthMapping, PADDING_SIZE);

        if(cellIndex == 0)
        {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }

        fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cell);
        if(cell.length() % 2 != 0)
        {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);

    }

}
