var jQuery = require("jquery");
require("jquery-easing");
require("popper.js");
require("bootstrap");
require("./sb-admin.min");
require("./angular/angular.components");

function denyUser(id){
    console.log("Ajax sent");
    jQuery.ajax({
        usl: "/create user",
        type: "DELETE"
    }).done(function(response){
        console.log(response);
    }).fail(function(response){
        console.log(response);
    });
}