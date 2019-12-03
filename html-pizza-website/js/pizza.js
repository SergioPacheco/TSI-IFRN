$(document).ready(function() {
  var timeout;
  var flipImages = function($container) {
    var amount = $container.data("amount");
    var current = $container.data("current");
    
    if(current >= amount){
      current = 1;
    } else {
      current = current + 1;
    }

    var dataAttr = "image" + current;
    var image = $container.data(dataAttr);
    $container.fadeOut(100, function() {
      $container.css("background-image", "url(" + image + ")");
      $container.fadeIn(100);
      $container.data("current", current);
    });
    timeout = setTimeout(function() {
      flipImages($container);
    }, 5000);
  };

  $(".ct-image").hover(
    function() {
      var $that = $(this);
      timeout = setTimeout(function() {
        flipImages($that);
      }, 1000);
    }, 
    function() {
      if(timeout) {
        clearTimeout(timeout);
      }
    }
    );
});


function myMap()
{

  myCenter=new google.maps.LatLng(-5.939326, -35.265830);
  var mapOptions= {
    center:myCenter,
    zoom:16, scrollwheel: false, draggable: false,
    mapTypeId:google.maps.MapTypeId.ROADMAP
  };
  var map=new google.maps.Map(document.getElementById("mapa"),mapOptions);

  var marker = new google.maps.Marker({
    position: myCenter,
  });
  marker.setMap(map);
}


/* Menu (pizza / salada / extra) */ 
function openMenu(evento, menuNome) {
  var i, x, tablinks;
  x = document.getElementsByClassName("menu");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < x.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" red", "");
  }
  document.getElementById(menuNome).style.display = "block";
  evento.currentTarget.firstElementChild.className += " red";
}
$(document).ready(function() { 
  document.getElementById("myLink").click(); 
  /* Menu (pizza / salada / extra) */ 
  function openMenu(evento, menuNome) {
    var i, x, tablinks;
    x = document.getElementsByClassName("menu");
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" red", "");
    }
    document.getElementById(menuNome).style.display = "block";
    evento.currentTarget.firstElementChild.className += " red";
  }
});