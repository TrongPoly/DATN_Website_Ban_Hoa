app.service('ToastService', ['$timeout', function($timeout) {
  var toastDetails = {
    timer: 5000,
    success: {
      icon: "fa-circle-check",
      text: "",
      type: "success",
    },
    error: {
      icon: "fa-circle-xmark",
      text: "",
      type: "error",
    },
    warning: {
      icon: "fa-triangle-exclamation",
      text: "",
      type: "warning",
    },
     info: {
      icon: "fa-circle-info",
      text: "",
      type: "info",
    },
    // Add other toast types here
  };

  this.createToast = function(id,text, toasts) {
    var toast = angular.copy(toastDetails[id]);
    toast.text = text;
    toasts.push(toast);
    toast.timeoutId = $timeout(function() {
      removeToast(toasts, toast);
    }, toastDetails.timer);
  };

  function removeToast(toasts, toast) {
    var index = toasts.indexOf(toast);
    if (index !== -1) {
      toasts.splice(index, 1);
    }
  }
}]);