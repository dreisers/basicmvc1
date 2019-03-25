package net.utility;

public class Paging {  
  /**
   * ?��?�� ?��?��?�� ?��?���?, 1 ?��?���?�??�� ?��?��
   * ?��?�� ?��?���?: 11 / 22   [?��?��] 11 12 13 14 15 16 17 18 19 20 [?��?��]
   * 
   * @param totalRecord ?���? ?��코드?��
   * @param nowPage     ?��?�� ?��?���?
   * @param recordPerPage ?��?���??�� ?��코드 ?�� 
   * @return
   */
  public String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭?�� ?��?���? ?��
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ?���? ?��?���? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ?���? 그룹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?��?�� 그룹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?��?�� 그룹?�� ?��?���? 목록 ?��?�� 
    int endPage = (nowGrp * pagePerBlock);             // ?��?�� 그룹?�� ?��?���? 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?��?�� ?��?���?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp >= 2){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?��?��</A>]");
    }

    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
 
      if (nowPage == i){ // ?��?�� ?��?���??���? 강조 ?���?
        str.append("<span style='font-size: 1.2em; font-weight: bold;'>"+i+"</span>&nbsp;");  
      }else{
        str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A>&nbsp;");
      }      
    }
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp < totalGrp){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?��?��</A>]");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
  
  /**
   * SPAN?��그�?? ?��?��?�� 박스 모델?�� �??��, 1 ?��?���?�??�� ?��?��
   * ?��?�� ?��?���?: 11 / 22   [?��?��] 11 12 13 14 15 16 17 18 19 20 [?��?��]
   * 
   * @param totalRecord ?���? ?��코드?��
   * @param nowPage     ?��?�� ?��?���?
   * @param recordPerPage ?��?���??�� ?��코드 ?�� 
   * @return
   */
  public String paging2(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭?�� ?��?���? ?��
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ?���? ?��?���? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ?���? 그룹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?��?�� 그룹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?��?�� 그룹?�� ?��?���? 목록 ?��?�� 
    int endPage = (nowGrp * pagePerBlock);             // ?��?�� 그룹?�� ?��?���? 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("    margin:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #CCCCCC;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("    margin:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?��?�� ?��?���?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?��?��</span></A>&nbsp;");
    }

    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
 
      if (nowPage == i){
        str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
      }else{
        str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
      }
    }
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp < totalGrp){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?��?��</span></A>&nbsp;");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
  
  /**
   * SPAN?��그�?? ?��?��?�� 박스 모델?�� �??��, 1 ?��?���?�??�� ?��?��
   * ?��?�� ?��?���?: 11 / 22   [?��?��] 11 12 13 14 15 16 17 18 19 20 [?��?��]
   * 
   * @param totalRecord ?���? ?��코드?��
   * @param nowPage     ?��?�� ?��?���?
   * @param recordPerPage ?��?���??�� ?��코드 ?�� 
   * @return
   */
  public String paging3(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // 블럭?�� ?��?���? ?��
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ?���? ?��?���? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ?���? 그룹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?��?�� 그룹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?��?�� 그룹?�� ?��?���? 목록 ?��?�� 
    int endPage = (nowGrp * pagePerBlock);             // ?��?�� 그룹?�� ?��?���? 목록 종료  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("  .span_box_1{");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("    margin:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #668db4;");
    str.append("    color: #FFFFFF;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("    margin:0px 0px 0px 0px; /*?��, ?��른쪽, ?��?��, ?���?*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?��?�� ?��?���?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?��?��</span></A>&nbsp;");
    }

    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
 
      if (nowPage == i){
        str.append("<span class='span_box_2'>&nbsp;"+i+"&nbsp;</span>&nbsp;");
      }else{
        str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'><span class='span_box_1'>&nbsp;"+i+"&nbsp;</span></A>&nbsp;");  
      }
    }
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10�? ?��?�� ?��?���?�? ?��?��
    if (nowGrp < totalGrp){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?��?��</span></A>&nbsp;");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
}  
