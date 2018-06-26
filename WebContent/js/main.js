// function Checkfiles(){
//     var fup = document.getElementById('afiles');
//     var fileName = fup.value;
//     var ext = fileName.substring(fileName.lastIndexOf('.') + 1);

//     if(ext =="jpeg" || ext=="png"){
//         return true;
//     }
//     else{
//         return false;
//     }
// }





// $("#fileUpload").on('change', function () {
 
//     if (typeof (FileReader) != "undefined") {
 
//         var image_holder = $("#image-holder");
//         image_holder.empty();
 
//         var reader = new FileReader();
//         reader.onload = function (e) {
//             $("<img />", {
//                 "src": e.target.result,
//                 "class": "thumb-image"
//             }).appendTo(image_holder);
//         }
//         image_holder.show();
//         reader.readAsDataURL($(this)[0].files[0]);
//     } else{
//         alert("Este navegador nao suporta FileReader.");
//     }
// });




$(document).ready(function() {
    $("#fileUpload").on('change', function() {
      //Get count of selected files
      var countFiles = $(this)[0].files.length;
      var imgPath = $(this)[0].value;
      var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
      var image_holder = $("#image-holder");
      image_holder.empty();
      if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
        if (typeof(FileReader) != "undefined") {
          //loop for each file selected for uploaded.
          for (var i = 0; i < countFiles; i++) 
          {
            var reader = new FileReader();
            reader.onload = function(e) {
              $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
              }).appendTo(image_holder);
            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[i]);
          }
        } else {
          alert("This browser does not support FileReader.");
        }
      } else {
        alert("Pls select only images");
      }
    });
  });