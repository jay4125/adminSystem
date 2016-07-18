/**
 * 存放公共js，每个页面都需要加载
 * Created by foxcj on 2015/10/21.
 */

/**
 * 设置左侧菜单
 * @param m
 */
function setMenu(m){
    $("#"+m).addClass("active");
    $("#"+m).parent("ul").css("display","block");
    $("#"+m).parent().parent("li:first").addClass("nav-parent active nav-active")
}

/**
 * cooke保存
 * @param cookieName
 * @param cookieValue
 * @param seconds
 */
function setCookie(cookieName, cookieValue, seconds) {
	
	//设置失效时间
	var expires = new Date();
	expires.setTime(expires.getTime() + seconds);
	
	//设置域名locahost
    var domain = "locahost";
    
    document.cookie = escape(cookieName) + "=" + escape(cookieValue) 
    + ";domain=" + domain + ";path=/;expires=" + exp.toGMTString();
}

/**
 * 获取cookie值
 * @param cookieName
 * @returns
 */
function getCookie(cookieName)
{
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]);
    return null;
}

/**
 * 删除cookie
 * @param cookieName
 */
function delCookie(cookieName)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 9999);
    var cval = getCookie(cookieName);
    if (cval != null) {
        setCookie(cookieName, '', exp);
    }
}

//重定向至本页面
function refThisPage() {
    window.location.href = window.location.href.replace(/#/g, '');
}

//全选某个复选框
function CheckAll(name,val) {
    $("input[name='" + name + "']").each(function () {
        this.checked = val;
    });
}

/*
 * 返回已选数据的值，以,号分隔
 * */
function returnCheckBox(name, getvaluetype) {
    var checkBox = $(":checkbox[name='" + name + "']:checked");
    if (checkBox.length > 0) {
        var isCheckVal = new Array();
        checkBox.each(function (i) {
            isCheckVal[i] = getvaluetype ? $(this).attr(getvaluetype) : $(this).attr("value");
        });
        return isCheckVal.join();
    } else {
        return false;
    }
}

function getLocalTimes(nS) {
    var dd = nS;
    if (nS == '' || nS == null) {
        dd = Date.parse(new Date());
    }
    var d = new Date(parseInt(dd));
    var year = d.getYear();
    var month = d.getMonth() + 1;
    var date = d.getDate();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds();
    return month + "月" + date + "日 " + hour + ":" + minute + ":" + second;
}


//验证
function verifyForm(objForm) {
    var from = $("#" + objForm);
    var ck = true;
    if (from.size() > 0) {
        var input = from.find("input:text,input:password,textarea,input:hidden,select");
        if (input.size() > 0) {
            var ma = new Array();
            input.each(function (index) {
                var inputOne = $(this);
                var v = inputOne.attr("v");
                var m = inputOne.attr("m");
                var len=inputOne.attr("len");
                if (v && m) {
                    var vs = v.split(",");
                    var vt;
                    var valueStr = $.trim(inputOne.val());
                    var valueStrLen=getChsAndEngStrLen(valueStr)
                    for (vt in vs) {
                        var tvt = vs[vt];
                        switch (tvt) {
                            case "required":
                                if (valueStr == '' || valueStr == ' ') {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ck = false;
                                    ma.push(m + "必须填写");
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "max":
                                if (valueStrLen > len) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ck = false;
                                    ma.push(m + "长度不能超过"+len+"个字符");
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "min":
                                if (valueStrLen < len) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ck = false;
                                    ma.push(m + "长度不能低于"+len+"个字符");
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "number":
                                if (isNaN(valueStr)) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "必须为数字");
                                    ck = false;
                                } else {
                                    inputOne.removeClass("req");
                                }
                                break;
                            case "int":
                                if (valueStr < 0 || valueStr != parseInt(valueStr)) {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "必须为正整数");
                                    ck = false;
                                } else {
                                    if (valueStr.indexOf(".") == -1) {
                                        inputOne.removeClass("req");
                                    } else {
                                        inputOne.addClass("req");
                                        inputOne.focus();
                                        ma.push(m + "不能有小数点,必须为正整数");
                                        ck = false;
                                    }
                                }
                                break;
                            case "email":
                                var reg = /^([a-z0-9A-Z]+[_|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
                                var test = reg.test(valueStr);
                                if (test) {
                                    inputOne.removeClass("req");
                                } else {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "格式不正确");
                                    ck = false;
                                }
                                break;
                            case "mobile":
                                var reg = /(^[1][0-9]{10}$)/;
                                var test = reg.test(valueStr);
                                if (test) {
                                    inputOne.removeClass("req");
                                } else {
                                    inputOne.addClass("req");
                                    inputOne.focus();
                                    ma.push(m + "格式不正确");
                                    ck = false;
                                }
                                break;
                        }
                        if (!ck) {
                            break;
                        }
                    }
                    if (!ck) {
                        alert(ma.join());
                        return false;
                    }
                }
            });
        } else {
            alert('在表单内未找到要检测的元素，请检查');
            ck = false;
        }
        return ck;
    } else {
        alert('要检测的表单为空，请检查');
        return false;
    }
}

