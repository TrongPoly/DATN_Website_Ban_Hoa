app.service('ToastService', function($timeout) {
  var toastDetails = {
    timer: 5000,
    success: {
      icon: "fa-circle-check",
      text: "Success: Đúng rồi!!!!.",
      type: "success",
    },
    error: {
      icon: "fa-circle-xmark",
      text: "Error: Sai rồi!!!.",
      type: "error",
    },
    // Add other toast types here
  };

  var toasts = [];

  this.getToasts = function() {
    return toasts;
  };

  this.createToast = function(id) {
    var toast = angular.copy(toastDetails[id]);
    toasts.push(toast);
    toast.timeoutId = $timeout(function() {
      this.removeToast(toast);
    }.bind(this), toastDetails.timer);
  };

  this.removeToast = function(toast) {
    var index = toasts.indexOf(toast);
    if (index !== -1) {
      toasts.splice(index, 1);
      $timeout.cancel(toast.timeoutId);
    }
  };
});