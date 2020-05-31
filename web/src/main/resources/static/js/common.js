var currentType = 'password';
$(document).ready(function () {
  $('#roleSelect').keydown(function (e) {
    // This will disable user typing on <input> tag while still user
    // can be clicking or else
    e.preventDefault();
    return false;
  });
  $('#genderSelect').keydown(function (e) {
      // This will disable user typing on <input> tag while still user
      // can be clicking or else
      e.preventDefault();
      return false;
    });
  $('#btnShowPass').click(function (event) {
    if (currentType === 'password') {
      $('#passwordField').attr('type', 'text');
      currentType = 'text';
    } else {
      $('#passwordField').attr('type', 'password');
      currentType = 'password';
    }
  });
  $('.item-role').click(function () {
    var currentSelectedStr = $('#roleSelect').val();
    if (currentSelectedStr === null || currentSelectedStr === undefined) {
        currentSelectedStr = '';
      }
    if (currentSelectedStr == 'Admin') {currentSelectedStr = '';}
    currentSelectedStr = currentSelectedStr === 'Click để chọn 1 hoặc nhiều' ? '' 
    : currentSelectedStr;

    var selectedText = $(this).text();
    if (selectedText == 'Admin') { currentSelectedStr == ''; }
    var isSelected = currentSelectedStr.includes(selectedText);
    if (!isSelected) {
      if (currentSelectedStr.length == 0) {
        currentSelectedStr = currentSelectedStr.concat(selectedText);
      } else {
        currentSelectedStr = currentSelectedStr.concat(', ' + selectedText);
      }
      $('#roleSelect').val(currentSelectedStr);
    } else {
      var currentSelectedStr = currentSelectedStr.replace(', ' + selectedText, '').replace(selectedText + ', ', '');
      $('#roleSelect').val(currentSelectedStr);
    }
  });
  $('.item-gender').click(function () {
    var selectedText = $(this).text();
    $('#genderSelect').val(selectedText);
  });
  $('.btn-show-pass').click(function (event) {
      var parent = $(this).parent();
      if (parent.siblings('input').attr('type') === 'password') {
          parent.siblings('input').attr('type', 'text');
      } else {
          parent.siblings('input').attr('type', 'password');
      }
    });
  
  $('#btnChangePass').click(function(event) {
      var currentPass = $('#currentPassword').val();
      var newPass = $('#newPassword').val();
      var repeatNewPass = $('#repeatNewPassword').val();
      if (!(currentPass != null && currentPass.length > 0)) {
          showNotification('top','center', "Vui lòng nhập mật khẩu hiện tại!", false)
          return;
      }
      if (!(newPass != null && newPass.length > 0)) {
          showNotification('top','center', "Vui lòng nhập mật khẩu mới!", false)
          return;
      }
      if (!(repeatNewPass != null && repeatNewPass.length > 0)) {
          showNotification('top','center', "Vui lòng nhập lại mật khẩu mới để xác nhận!", false)
          return;
      }
      if (repeatNewPass != newPass) {
          showNotification('top','center', "Nhập lại mật khẩu mới không trùng khớp!", false)
          return;
      }
      changePassword(currentPass,newPass);
  });
  
  var errorMess = $('#error_message').val();
  if (errorMess != null && errorMess.length > 0) {
      var errorState = $('#error_state').val();
      showNotification('top','center', errorMess, errorState ? errorState : false)
  }
});

function showNotification(from, align, message, success) {
    color = success == true ? 'success' : 'danger';

    $.notify({
      icon: "nc-icon nc-bell-55",
      message: message
    }, {
      type: color,
      timer: 4000,
      placement: {
        from: from,
        align: align
      }
    });
  }

function changePassword(currentPass, newPassword) {
    $.ajax({
        type : "POST",
        url : "/user/change_pass",
        dataType : "json",
        contentType : "application/json; charset=utf-8",
        data : JSON.stringify({
            "currentPassword" : currentPass,
            "newPassword" : newPassword
        }),
        success : function (result) {
            showNotification('top','center', result.message, true)

            console.log(result.message);
        },
        error : function (response, status, error) {
            // Show notification error with bootstrap 4
            showNotification('top','center', response.responseJSON.message, false)
            console.log(response.responseJSON.message);
        }
    });
}
