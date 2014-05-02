function brandList(brandJSON, partsUrl) {
  var container = $('.brand-container'),
      wraper = $('.brand-wraper'),
      toggle = $('#stopShowStart'),
      swipeGuide = $('.iconHand'),
      rowElement = '<div class="row-fluid row-item brandsSection">',
      brand = rowElement,
      elements = brandJSON.length,
      resizeTimer, zsliderMobile;

      $(window).on('resize',function(){
          clearTimeout(resizeTimer);
          resizeTimer = setTimeout(resizeTrigger,1000);
      });



        $.each(brandJSON, function(i,n){
          //loop through JSON and get the values
          //console.log("The css for the brand "+n["name"]+" is "+n["cssName"]);
          if(i > 0 && i%6 === 0){
            brand += '</div>' + rowElement;
          }
          brand += '<div class="span2"><a href="'+ partsUrl + '/partsdirect/brands/'+ n["name"] +'-Parts"><i class="'+ n["cssName"]+' many"></i></a></div>';
        });

        brand += '</div>';
        wraper.append(brand);

        initSlideCarrol.init();

        resizeTrigger();

        function resizeTrigger(){
        if (window.matchMedia && window.matchMedia("(max-width: 767px)").matches) {
          initSlideCarrol.stopStartAnimation(true);
          container.addClass("blockLevel");
          swipeView();
        }else{
              if (typeof zsliderMobile !== 'undefined') {
                zsliderMobile.off();
              }
              if(elements > 6){
                container.removeClass("blockLevel");
                initSlideCarrol.runEvents();
                $(toggle).show();
              }else{
                container.addClass("blockLevel");
              }
        }
      }

      function sliderReleased(){
        setTimeout(function(){
          swipeGuide.hide();
        }, 1000);
      }

        function swipeView(){
          if(typeof zsliderMobile === 'undefined'){
          zsliderMobile = new zem.Slider('.brand-container', {
                  wrapper: '.brand-wraper',
                  items: '.brandsSection',
                  forceTouch: true,
                  onRelease: sliderReleased
              });
          /*
          container.hammer().on('dragright', function(e) {
              e.gesture.stopDetect();
              zsliderMobile.prev();
          });

         container.hammer().on('dragleft', function(e) {
              zsliderMobile.next();
              e.gesture.stopDetect();
         });*/



        }else{
          zsliderMobile.on();
        }



        }

}

var initSlideCarrol = (function ($) {
    var slideParent,
        slides,
        openCloseArrows,
        count,
        slidesHeight,
        current = 0,
        isAnimating = false,
        isOpen = false,
        setCSSCurrent, resetCSSCurrent, showAllLogos, closeAllLogos,
        runCarol = 0,
        timeout = 0,
        stopStartAnimation, runSliderThing, runEvents,
        currentSlide;

    setCSSCurrent = {
        top: 0,
        zIndex: 10
    };
    resetCSSCurrent = {
        top: "100%",
        zIndex: 0
    };

    stopStartAnimation = function (animationStart) {
        if (animationStart){
          //stop
          stopAnima();
          showAllLogos();
          slideParent.removeAttr('style');
        }else{
          closeAllLogos();
          openCloseArrows.off().on('click', function (event) {
            event.preventDefault();
            if (isOpen) {
                // run go
                closeAllLogos();
            } else {
                //stop
                stopAnima();
                showAllLogos();
            }
          });
        }

    };

    var stopAnima = function () {
        isAnimating = false;
        window.clearInterval(runCarol);
        window.clearTimeout(timeout);
      };

    showAllLogos = function () {
      isOpen = true;
        slideParent.removeAttr('style');
        slides.css("top", "auto").removeClass("fadeOutSlide");
        slideParent.css("height", slidesHeight);
        slideParent.removeClass("default").addClass("blockLevel");
    };

    closeAllLogos = function () {
      isOpen = false;
        slideParent.removeAttr('style');
        slideParent.removeClass("blockLevel").addClass("default");
        slides.removeAttr('style');
        current = 0;
        initSlideCarrol.runSliderThing();
    };



    runSliderThing = function () {
        currentSlide.css(setCSSCurrent);
        stopAnima();
        isAnimating = true;
        runCarol = window.setInterval(function () {

            var currentItem = current;
            slides.css(resetCSSCurrent).removeClass("fadeOutSlide");

            slides.eq(currentItem).css({
                top: "-100%",
                zIndex: 1
            }).addClass('fadeOutSlide');

            timeout = setTimeout(function(){slides.eq(currentItem).css(resetCSSCurrent).removeClass("fadeOutSlide");},3000);

            current < count - 1 ? ++current : current = 0;

            slides.eq(current).css(setCSSCurrent);

        }, 4000);
    };

    runEvents = function () {
        count = slides.length;
        slidesHeight = count * slides.height();
        currentSlide = slides.eq(current);
        initSlideCarrol.stopStartAnimation();
        initSlideCarrol.runSliderThing();
    };

    init = function() {
      slideParent = $('#slider-caro-applianceV2');
      slides = $('.row-item');
      openCloseArrows = $('#stopShowStart');
    };

    return {
        runSliderThing: runSliderThing,
        runCarol: runCarol,
        closeAllLogos: closeAllLogos,
        stopStartAnimation: stopStartAnimation,
        showAllLogos: showAllLogos,
        runEvents: runEvents,
        init: init
    };

}(window.jQuery));