var stickyItem;

stickyItem = (function() {
    function stickyItem(selector) {
        this.$set = $(selector);
        if (this.$set.length === 0) {
            this.$set = false;
        } else {
            this.setBreakPoint().setClassToggles();
        }
    }

    stickyItem;

    stickyItem.prototype.setBreakPoint = function(dir, breakpoint) {
        this.dir = dir != null ? dir : 'top';
        this.breakpoint = breakpoint != null ? breakpoint : false;
        if (this.breakpoint === false && this.$set !== false) {
            this.breakpoint = this.$set.offset()['top'];
        }
        return this;
    };

    stickyItem.prototype.setClassToggles = function(classOn, classOff) {
        this.classOn = classOn != null ? classOn : 'active';
        this.classOff = classOff != null ? classOff : 'inactive';
        return this;
    };

    stickyItem.prototype.isset = function() {
        if (this.$set !== false) {
            return true;
        } else {
            return false;
        }
    };

    stickyItem.prototype.setMaxScroll = function(maxScroll) {
        this.maxScroll = maxScroll != null ? maxScroll : false;
        return this;
    };

    stickyItem.prototype.setCallBack = function(callback) {
        if (callback == null) {
            callback = false;
        }
        if (callback !== false && typeof callback === "function") {
            return this.callback = callback;
        }
    };

    stickyItem.prototype.setOnCallback = function(onCallback) {
        this.onCallback = onCallback != null ? onCallback : false;
        return this;
    };

    stickyItem.prototype.setOffCallback = function(offCallback) {
        this.offCallback = offCallback != null ? offCallback : false;
        return this;
    };

    stickyItem.prototype.callOnCallback = function() {
        if (typeof this.onCallback === "function") {
            return this.onCallback();
        }
    };

    stickyItem.prototype.callOffCallback = function() {
        if (typeof this.offCallback === "function") {
            return this.offCallback();
        }
    };

    stickyItem.prototype.checkState = function(val) {
        var delta;
        if (this.$set !== false) {
            if (val > this.breakpoint && !this.$set.hasClass(this.classOn)) {
                this.$set.removeClass(this.classOff).addClass(this.classOn);
                this.callOnCallback();
            } else if (val <= this.breakpoint && !this.$set.hasClass(this.classOff)) {
                this.$set.removeClass(this.classOn).addClass(this.classOff);
                this.callOffCallback();
            }
            if (val > this.maxScroll) {
                delta = this.maxScroll - val;
                this.$set.css({
                    marginTop: delta
                });
            }
            if (typeof this.callback === 'function') {
                this.callback(val);
            }
        }
        return this;
    };

    return stickyItem;

})();

$(document).ready((function(_this) {
    return function() {
        var modelPartListDiagram = new stickyItem('#selector');
        modelPartListDiagram.setBreakPoint('top');
        modelPartListDiagram.setClassToggles('fixed','sticky');
        $(window).scroll(function(){
           modelPartListDiagram.checkState( $(window).scrollTop() );
        });
        var $consultBox, $footer, $pgHeader, $repairNav, $scBtn, $tabReminder, consultBoxIsOnPage, consultationBreakpoint, footerDepth, heroImg, isImprovePage, isTabsHSlayout, mobileBrowser, navTopBar, railHeight, scrollDist;
        mobileBrowser = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
        if (!mobileBrowser && $(window).width() > 980) {
            $pgHeader = new stickyItem('#pageNav');
            $pgHeader.setBreakPoint('top');
            $pgHeader.setClassToggles('sticky', 'unsticky');
            isTabsHSlayout = $('.heroTabsHSlayout');
            isImprovePage = $('.moduleHero');
            if (isTabsHSlayout.length) {
                navTopBar = $('.pageNavWrapper').height();
                heroImg = $('#repairHeroBlock');
                consultationBreakpoint = consultationBreakpoint = heroImg.offset()['top'] + (heroImg.height() - navTopBar);
                $repairNav = new stickyItem('#pageNavLinks');
                $scBtn = new stickyItem('.btnSchedulrShowHide');
                $tabReminder = new stickyItem('.tabSelectItems');
                $repairNav.setBreakPoint('top', consultationBreakpoint);
                $repairNav.setClassToggles('show', 'hidden');
                $tabReminder.setBreakPoint('top', consultationBreakpoint);
                $tabReminder.setClassToggles('tabsOutOfView', 'tabsInView');
                $scBtn.setBreakPoint('top', consultationBreakpoint);
                $scBtn.setClassToggles('show', 'hidden');
            } else if (isImprovePage.length) {
                navTopBar = $('.pageNavWrapper').height();
                heroImg = $('#viewHeroDefault');
                consultationBreakpoint = heroImg.offset()['top'] + heroImg.height() - navTopBar;
                $repairNav = new stickyItem('#pageNavLinks');
                $repairNav.setBreakPoint('top', consultationBreakpoint);
                $repairNav.setClassToggles('show', 'hidden');
            }
            $pgHeader.setOnCallback(function() {
                var $subMenu;
                $subMenu = $('#json_submenus_container');
                $subMenu.find('.megamenu').trigger('mouseleave');
                return $subMenu.css('height', '0px');
            });
            window.$rightRail = new stickyItem('#rightRail');
            $rightRail.setBreakPoint('top' + 21, $pgHeader.breakpoint - 21);
            $rightRail.setClassToggles('sticky', 'unsticky');
            consultBoxIsOnPage = $('#rightRail-improve');
            if (consultBoxIsOnPage.length) {
                heroImg = $('#hiLeadsheetWrap');
                navTopBar = $('#pageNav').height();
                consultationBreakpoint = heroImg.offset()['top'] + heroImg.height() - navTopBar;
                $consultBox = new stickyItem('#ihScheduleLeadButton');
                $consultBox.setBreakPoint('top', consultationBreakpoint);
                $consultBox.setClassToggles('show', 'hidden');
            }
            $footer = $('.footer_container');
            if ($footer.length === 1) {
                railHeight = $('#rightRail').height();
                footerDepth = $footer.offset()['top'];
                $pgHeader.maxScroll(footerDepth);
                $rightRail.maxScroll(footerDepth + railHeight);
            }
            $rightRail.setCallBack(function(scrollDist) {
                var mainfooterOff, railSpot, stickyFootCheck;
                if (scrollDist == null) {
                    scrollDist = false;
                }
                if (scrollDist === false) {
                    return;
                }
                mainfooterOff = $('.mainFooter').offset();
                if (typeof mainfooterOff === "undefined") {
                    mainfooterOff = $('#footer').offset();
                }
                railSpot = $('#rightRail').height() + 174;
                if ($('.js_rhrCollide').length > 0) {
                    mainfooterOff = $('.js_rhrCollide').offset();
                } else {
                    stickyFootCheck = 0;
                }
                stickyFootCheck = mainfooterOff.top - (scrollDist + railSpot);
                if (stickyFootCheck < 0) {
                    $('#rightRail').css('margin-top', stickyFootCheck + 34);
                } else if (stickyFootCheck >= 0) {
                    $('.right_rail.sticky').css('margin-top', 34);
                }
                if ($('.right_rail.unsticky').length) {
                    return $('.right_rail.unsticky').css('margin-top', 0);
                }
            });
            scrollDist = $(window).scrollTop();
            $pgHeader.checkState(scrollDist);
            $rightRail.checkState(scrollDist);
            if (consultBoxIsOnPage.length) {
                $consultBox.checkState(scrollDist);
            }
            if (isTabsHSlayout.length) {
                $repairNav.checkState(scrollDist);
                $scBtn.checkState(scrollDist);
                $tabReminder.checkState(scrollDist);
            }
            if (isImprovePage.length) {
                $repairNav.checkState(scrollDist);
            }
            return $(window).scroll(function() {
                scrollDist = $(window).scrollTop();
                $pgHeader.checkState(scrollDist);
                $rightRail.checkState(scrollDist);
                if (consultBoxIsOnPage.length) {
                    $consultBox.checkState(scrollDist);
                }
                if (isTabsHSlayout.length) {
                    $repairNav.checkState(scrollDist);
                    $scBtn.checkState(scrollDist);
                    $tabReminder.checkState(scrollDist);
                }
                if (isImprovePage.length) {
                    return $repairNav.checkState(scrollDist);
                }
            });
        }
    };
})(this));
