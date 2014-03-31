
var ResponsivePicture;

ResponsivePicture = (function() {

    function ResponsivePicture(elem) {
        this.elem = elem;
        this.newActive = false;
        this.active = 0;
        this.srcs = [];
        this._getSources();
        this._bind();
    }

    ResponsivePicture.prototype._getSources = function() {
        var _this = this;
        this.elem.find('span').each(function(index, elem) {
            _this.srcs.push($(elem));
            return true;
        });
        return true;
    };

    ResponsivePicture.prototype._checkSources = function(width) {
        var index, pic, _i, _len, _ref;
        _ref = this.srcs;
        for (index = _i = 0, _len = _ref.length; _i < _len; index = ++_i) {
            pic = _ref[index];
            if (!this.newActive && pic.data('default') === "true") {
                this.newActive = index;
            }
            if (width > pic.data('min') && width < pic.data('max')) {
                this.newActive = index;
            }
        }
        if (this.newActive !== false && this.newActive !== this.active) {
            this._activate(this.srcs[this.newActive]);
            this._deactivate(this.srcs[this.active]);
            this.active = this.newActive;
            return this.newActive = false;
        }
    };

    ResponsivePicture.prototype._imageTag = function(src) {
        return "<img src=\"" + src + "\" alt=\"" + (this.elem.data('alt')) + "\"/>";
    };

    ResponsivePicture.prototype._activate = function(pic) {
        var img;
        img = this._imageTag(pic.data('src'));
        return pic.html('').append(img);
    };

    ResponsivePicture.prototype._deactivate = function(pic) {
        return pic.html('');
    };

    ResponsivePicture.prototype._bind = function() {
        var _this = this;
        return $(document).on('debounceResize', function() {
            console.log('resizing');
            return _this._checkSources($(window).width());
        });
    };

    return ResponsivePicture;

})();
