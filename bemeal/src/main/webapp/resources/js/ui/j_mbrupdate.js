function modifyUI(){
	return '<div id="memberModify">'
    +'<form id="memberModify">'
    +'<h2>마이페이지</h2>'
        +'<div class="container">'
            +'<hr>'
        +'<label for="member_id"><b>아이디</b></label><br>'
        +'<input type="text" name="member_id" value='+x.member.memberId+' readonly><br>'
        +'<label for="password"><b>비밀번호</b></label><br>'
        +'<input type="text" name="password" value='+x.member.password+' required><br>'
        +'<label for="name"><b>이름</b></label><br>'
        +'<input type="text" name="name" value='+x.member.name+' readonly><br>'
        +'<label for="ssn"><b>주민번호</b></label><br>'
        +'<input type="text" name="ssn" value='+x.member.ssn+' readonly><br>'
        +'<label for="address"><b>주소</b></label><br>'
        +'<input type="text"  name="address" value='+x.member.address+' required><br>'
        +'<label for="e_mail"><b>이메일 주소</b></label><br>'
        +'<input type="text" name="e_mail" value='+x.member.eMail+' required><br>'
        +'<label for="phone_num"><b>전화번호</b></label><br>'
        +'<input type="text" name="phone_num" value='+x.member.phoneNum+' required><br>'
        +'<div class="clearfix">'
        +'<button type="submit" id="modify_submit_btn" class="j_button">정보수정</button><br>'
        +'<button type="submit" id="delete_submit_btn" class="j_red_button">회원탈퇴</button>'
        +'</div>'
        +'</div>'
    +'</form>'
    +'</div>'
}
