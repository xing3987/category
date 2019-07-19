<%@ page import="sun.security.util.Password" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title> 生鲜管理2018</title>
    <script type="text/javascript" src="static/js/jquery-1.9.0.min.js"></script>
    <script type="text/javascript" src="static/js/login.js"></script>
    <link href="static/css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>
    生鲜管理系统<sup>V2018</sup>
</h1>

<div class="login" style="margin-top: 50px;">
    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin"
               href="javascript:void(0);" tabindex="7">快速登录</a> <a
                class="switch_btn" id="switch_login" href="javascript:void(0);"
                tabindex="8">快速注册</a>
            <div class="switch_bottom" id="switch_bottom"
                 style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>
    <div class="web_qr_login" id="web_qr_login"
         style="display: block; height: 300px;">
        <!--登录-->
        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <form action="Login"
                          accept-charset="utf-8" id="login_form" class="loginForm"
                          method="post">
                        <input type="hidden" name="method" value="login"/>
                        <input type="hidden" name="did" value="0"/> <input type="hidden"
                                                                           name="to" value="log"/>

						
                       <!--	用session记住帐号密码
                        	HttpSession s=request.getSession();
                        	String name=(String)s.getAttribute("rightUser");
                        	String password=(String)s.getAttribute("rightPassword");
                        	if(name==null){
                        		name="";
                        	}
                         	if(password==null){
                        		password="";
                        	}
                        -->  
                         
                          <%	//用cookie记住帐号密码
                            String name="";String password="";
                        	Cookie[] cookies=request.getCookies();
                        	if(cookies!=null){
	                          	for(Cookie cookie:cookies){
	                          		if(cookie.getName().equals("rightUser")){
	                          		    name=cookie.getValue();
	                          		}
	                          		if(cookie.getName().equals("rightPassword")){
	                          		    password=cookie.getValue();
	                          		}
	                          	}
                        	}
                         %>                                                                                                                             
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input type="text" id="u" name="name" value="<%=name%>" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">
                                <input type="password" id="p" name="password" value="<%=password%>" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="check" id="check">
                            <label style="font-size:16px">验证码：</label>
                               <input type="text"  name="checkcode" style="width:120px;height:30px">
                               <!-- 在不改变地址的情况下刷新图片,加上？号和一个随机数,该函数写在onclick中每次点击随机生成 -->
                               <img src="/Checkcode" style="magin-top:5px" onclick="this.src='/Checkcode?'+Math.random()">
                        </div>

                        <div style="margin-left: 45px;margin-top: 10px">
                            <input style="vertical-align: middle;" type="checkbox" value="yes" name="remember">记住密码 
                            <%
                           		String msg="请输入验证码";
 								String checkmsg=(String)request.getAttribute("checkcode_fail");
                            	if(checkmsg!=null){
                            		msg=checkmsg;
                            	}
                            	if((String)request.getAttribute("login_false")!=null){
                            		msg=(String)request.getAttribute("login_false");
                            	}
                            %> 
                            <span style="color:red;font-size:1.2em"> <%=msg %></span><br/>

                        </div>

                        <div style="padding-left: 50px; margin-top: 20px;">
                            <input type="submit" value="登 录" style="width: 150px;"
                                   class="button_blue"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--登录end-->
    </div>
    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none;">
        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8"
                  action="AddUser" method="post">
                <input type="hidden" name="to" value="reg"/> <input type="hidden"
                                                                    name="did" value="0"/>
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">快速注册请注意格式</div>
                    <li><label for="user" class="input-tips2">用户名：</label>
                        <div class="inputOuter2">
                            <input type="text" id="user" name="name" maxlength="16"
                                   class="inputstyle2"/>
                        </div>
                    </li>
                    <li><label for="passwd" class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd" name="password" maxlength="16"
                                   class="inputstyle2"/>
                        </div>
                    </li>
                    <li><label for="passwd2" class="input-tips2">确认密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd2" name="password2" maxlength="16"
                                   class="inputstyle2"/>
                        </div>
                    </li>

                    <li><label for="email" class="input-tips2">邮箱：</label>
                        <div class="inputOuter2">
                            <input type="email" id="email" name="email" class="inputstyle2"/>
                        </div>
                    </li>
                    <li>
                        <div class="inputArea">

                            <input type="submit" id="reg"
                                   style="margin-top: 10px; margin-left: 85px;"
                                   class="button_blue" value="同意协议并注册"/> <a href="#" class="zcxy"
                                                                            target="_blank.">注册协议</a>
                        </div>
                    </li>
                    <div class="cl"></div>
                </ul>
            </form>
        </div>
    </div>
    <!--注册end-->
</div>
</body>
</body>
</html>