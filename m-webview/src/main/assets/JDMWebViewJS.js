(function(){
    console.log("这是我家的小狗");
    var objs = document.getElementsByTagName("img");
    var timeout;
    var startOffsetX;
    var startOffsetY;
    for(var i=0;i<objs.length;i++){
        objs[i].addEventListener('touchstart', function(event) {
            touch = event.touches[0];
            startevent = event;
            startOffsetX = Number(touch.pageX);
            startOffsetY = Number(touch.pageY);
            timeout = setTimeout(function () {
                window.location.href = 'jmimage:' + event.srcElement.currentSrc
            },700);
        });
        objs[i].addEventListener('touchmove', function(event) {
            touch = event.touches[0];
            var touchMoveX = Math.abs(Number(touch.pageX) - startOffsetX);
            var touchMoveY = Math.abs(Number(touch.pageY) - startOffsetY);
            if (touchMoveX > 10 || touchMoveY > 10) {
                clearTimeout(timeout);
            } else {
                event.preventDefault();
            }
        });
        objs[i].addEventListener('touchend', function(event) {
            event.preventDefault();
            clearTimeout(timeout);
        });
    }
     console.log("这是你家的小狗");
})();
