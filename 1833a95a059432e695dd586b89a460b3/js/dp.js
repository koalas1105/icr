﻿function doPrint()
{
	var Title = document.all.Title.innerHTML;
	var mContent = document.all.Content.innerHTML;

var PartI = ' \
<style type="text/css"><!-- \
--></style><table width="600" border="0" cellspacing="0" cellpadding="0" align="center" bordercolor="#FFCC66" > \
	 <tr>  <td bgcolor="#FFCC00" width="465"><b><font class="font-family: 宋体 ;font-size: 9pt">欢迎访问中国政府门户网站</font>  <font face="Verdana, Arial, Helvetica, sans-serif" size="1"> - WWW.GOV.CN</font></b> </td> \
    <td bgcolor="#FFDC71" width="23">&nbsp;</td>    <td bgcolor="#FFE8A2" width="13">&nbsp;</td>    <td bgcolor="#FFF1BB" width="12">&nbsp;</td>    <td bgcolor="#FFF8D9" width="15">&nbsp;</td> \
    <td bgcolor="#FFF9DD" width="34">&nbsp;</td>  </tr>  <tr bgcolor="#EAEAEA">     <td class="p9" colspan="6">       <div align="right"><font color="#000000"> ';

var PartII = '<font size="1"><b>&gt;&gt;</b></font></font></div>    </td>  </tr>  <tr><td colspan="6">      <hr size="1" noshade>    </td></tr>  <tr valign="top" align="left"> \
    <td colspan="6">       <table width="100%" border="0" cellspacing="0" cellpadding="5" align="center" class="main">        <tr>           <td>  ';
	
var PartIII= '<div align="center"><b>'+Title+'</b></div>';
var Part4='</td></tr><tr><td> <div align="center" class="p9">'+'</div>';
var Part5='</td></tr><tr><td align="center">'+'</td></tr><tr> <td>'+mContent+'</td></tr></table> </td></tr> <tr> <td colspan="6">      <hr noshade size="1"></td>  </tr>  <tr>     <td bgcolor="#FFCC66" colspan="6">       <div align="right" class="p9"><b><font face="宋体" font-size="9pt">中国政府门户网站版权所有</b></font></div> </td>  </tr></table>';
document.body.innerHTML = PartI+PartII+PartIII+Part4+Part5;
window.print();

}

function validate_form() {

  var str0,str1,str2,str3,str4,str;

  validity = true; // assume valid

  if (!check_email(document.Form2000.FriendEmail.value))

        { validity = false; alert(' 朋友的Email可能打错了或为空的！');}

  if (validity)
  {
	str0="◆◆◆中国政府门户网站◆◆◆"
	str1="您好!";
	str2="您的朋友向您推荐中国政府门户网站信息:";
	str3="“"+document.title+"”"+"\n链接网址是：";
	str4=this.location;
	str=str0+"\n"+str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n";
	document.Form2000.Context.value=str;

	document.Form2000.action="mailto:"+document.Form2000.FriendEmail.value+"?\&amp;Subject=网站信息";
  }
  return validity;
}

function check_email(address) {

  if ((address == "")

    || (address.indexOf ('@') == -1)

    || (address.indexOf ('.') == -1))

      return false;

  return true;
}
