/*! Select2 4.0.5 | https://github.com/select2/select2/blob/master/LICENSE.md */

(function () {
    if (jQuery && jQuery.fn && jQuery.fn.select2 && jQuery.fn.select2.amd) var e = jQuery.fn.select2.amd;
    return e.define("select2/i18n/en", [], function () {
        return {
            errorLoading: function () {
                return "Натижани юклашда хатолик юз берди."
            }, inputTooLong: function (e) {
                var t = e.input.length - e.maximum, n = "Илтимос " + t + " та ҳарфни ўчиринг";
                return t != 1 && (n += "s"), n
            }, inputTooShort: function (e) {
                var t = e.minimum - e.input.length, n = "Илтимос камида " + t + " та ҳарф киритинг";
                return n
            }, loadingMore: function () {
                return "Қолган натижалар юкланмоқда…"
            }, maximumSelected: function (e) {
                var t = "Сиз максимум " + e.maximum + " ни танлай оласиз";
                return e.maximum != 1 && (t += "s"), t
            }, noResults: function () {
                return "Топилмади"
            }, searching: function () {
                return "Изламоқда…"
            }
        }
    }), {define: e.define, require: e.require}
})();