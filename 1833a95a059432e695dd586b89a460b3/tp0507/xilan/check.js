﻿function searchalert(){
validate=true;
if(document.formsearch.searchword.value=="")
{
validate=false;
alert('请输入关键字!');
}
else
validate=true;
return validate;
}

function validate_form() {

  var str0,str1,str2,str3,str4,str;

  validity = true; // assume valid

  if (!check_email(document.Form2000.FriendEmail.value))

        { validity = false; alert(' 朋友的Email可能打错了或为空的！');}

  if (validity)
  {
	str0="◆◆◆中国政府网◆◆◆"
	str1="您好!";
    str2="您的朋友向您推荐中国政府网信息:";
	str3="“"+document.title+"”"+"\n链接网址是：";
	str4=this.location;
	str=str0+"\n"+str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n";
	document.Form2000.Context.value=str;

	document.Form2000.action="mailto:"+document.Form2000.FriendEmail.value+"?\&amp;Subject=推荐信息";
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
