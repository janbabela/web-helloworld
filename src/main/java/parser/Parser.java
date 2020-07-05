package parser;

public class Parser {

  public Parser() {
  }

  public String[][] parseAll(String parsingString) {
    return parseCells(parseRows(parsingString));
  }


  private String[] parseRows(String parsingString) {

    String[] result = new String[25];
    int rowIndex = 0;

    parsingString = parsingString.substring(1);
    for (int i=0; i<25; i++) {
      int start = parsingString.indexOf('[');
      int end = parsingString.indexOf(']');
      result[rowIndex] = parsingString.substring(start,end);
      parsingString = parsingString.substring(end+2);
      rowIndex++;
    }

    return result;
  }

  private String[][] parseCells(String[] parsedRows) {

    String[][] result = new String[25][25];

    for (int i=0; i<25; i++) {
      int cellIndex = 0;
      parsedRows[i] = parsedRows[i].substring(2);
      for (int j = 0; j < 24; j++) {
        int end = parsedRows[i].indexOf(',');
        result[i][cellIndex] = parsedRows[i].substring(0,end-1);
        parsedRows[i] = parsedRows[i].substring(end+2);
        cellIndex++;
      }
      result[i][cellIndex] = parsedRows[i].substring(0,parsedRows[i].length()-2);
    }

    return result;
  }

}
