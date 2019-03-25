package net.utility;

public class Paging {  
  /**
   * ?ˆ«? ?˜•?ƒœ?˜ ?˜?´ì§?, 1 ?˜?´ì§?ë¶??„° ?‹œ?‘
   * ?˜„?¬ ?˜?´ì§?: 11 / 22   [?´? „] 11 12 13 14 15 16 17 18 19 20 [?‹¤?Œ]
   * 
   * @param totalRecord ? „ì²? ? ˆì½”ë“œ?ˆ˜
   * @param nowPage     ?˜„?¬ ?˜?´ì§?
   * @param recordPerPage ?˜?´ì§??‹¹ ? ˆì½”ë“œ ?ˆ˜ 
   * @return
   */
  public String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ? „ì²? ?˜?´ì§? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘ 
    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ  
    
    StringBuffer str = new StringBuffer();
    
    str.append("<style>");
    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}");
    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}");
    str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; color:black; font-size: 1em;}");
    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?˜„?¬ ?˜?´ì§?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp >= 2){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?´? „</A>]");
    }

    for(int i=startPage; i<=endPage; i++){
      if (i > totalPage){
        break;
      }
 
      if (nowPage == i){ // ?˜„?¬ ?˜?´ì§??´ë©? ê°•ì¡° ?š¨ê³?
        str.append("<span style='font-size: 1.2em; font-weight: bold;'>"+i+"</span>&nbsp;");  
      }else{
        str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A>&nbsp;");
      }      
    }
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp < totalGrp){
      str.append("[<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?‹¤?Œ</A>]");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
  
  /**
   * SPAN?ƒœê·¸ë?? ?´?š©?•œ ë°•ìŠ¤ ëª¨ë¸?˜ ì§??›, 1 ?˜?´ì§?ë¶??„° ?‹œ?‘
   * ?˜„?¬ ?˜?´ì§?: 11 / 22   [?´? „] 11 12 13 14 15 16 17 18 19 20 [?‹¤?Œ]
   * 
   * @param totalRecord ? „ì²? ? ˆì½”ë“œ?ˆ˜
   * @param nowPage     ?˜„?¬ ?˜?´ì§?
   * @param recordPerPage ?˜?´ì§??‹¹ ? ˆì½”ë“œ ?ˆ˜ 
   * @return
   */
  public String paging2(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ? „ì²? ?˜?´ì§? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘ 
    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ  
    
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
    str.append("    padding:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("    margin:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #CCCCCC;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("    margin:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?˜„?¬ ?˜?´ì§?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?´? „</span></A>&nbsp;");
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
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp < totalGrp){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?‹¤?Œ</span></A>&nbsp;");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
  
  /**
   * SPAN?ƒœê·¸ë?? ?´?š©?•œ ë°•ìŠ¤ ëª¨ë¸?˜ ì§??›, 1 ?˜?´ì§?ë¶??„° ?‹œ?‘
   * ?˜„?¬ ?˜?´ì§?: 11 / 22   [?´? „] 11 12 13 14 15 16 17 18 19 20 [?‹¤?Œ]
   * 
   * @param totalRecord ? „ì²? ? ˆì½”ë“œ?ˆ˜
   * @param nowPage     ?˜„?¬ ?˜?´ì§?
   * @param recordPerPage ?˜?´ì§??‹¹ ? ˆì½”ë“œ ?ˆ˜ 
   * @return
   */
  public String paging3(int totalRecord, int nowPage, int recordPerPage, String col, String word, String filenm){
    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜
    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ? „ì²? ?˜?´ì§? 
    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹
    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹
    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘ 
    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ  
    
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
    str.append("    padding:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("    margin:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("  }");
    str.append("  .span_box_2{");
    str.append("    background-color: #668db4;");
    str.append("    color: #FFFFFF;");
    str.append("    font-size: 1em;");
    str.append("    border: 1px;");
    str.append("    border-style: solid;");
    str.append("    border-color: #cccccc;");
    str.append("    padding:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("    margin:0px 0px 0px 0px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/");
    str.append("  }");
    str.append("</style>");
    str.append("<DIV id='paging'>");
    //str.append("?˜„?¬ ?˜?´ì§?: " + nowPage + " / " + totalPage + "&nbsp;&nbsp;");

    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp >= 2){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?´? „</span></A>&nbsp;");
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
    
    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™
    if (nowGrp < totalGrp){
      str.append("<A href='./"+filenm+"?col="+col+"&word="+word+"&nowPage="+_nowPage+"'><span class='span_box_1'>?‹¤?Œ</span></A>&nbsp;");
    }
    str.append("</DIV>");
    
    return str.toString();
  }
}  
