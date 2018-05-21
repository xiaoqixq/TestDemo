
(function(){
    var edu = {
    };

    var match,
        pl     = /\+/g,
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) { return decodeURIComponent(s.replace(pl, " ")); },
        query  = window.location.search.substring(1),
        urlParams = {};
    while (match = search.exec(query)){
        urlParams[decode(match[1])] = decode(match[2]);
    }
    edu.urlParams = urlParams;

    Promise.prototype.fail = Promise.prototype["catch"];
    Promise.prototype.success = Promise.prototype.then;

    edu.Defer = function(){
        var me = this;
        me.promise = new Promise(function(resolve,reject){
            me.resolve = resolve;
            me.reject = reject;
        });
    };

    var APPLICATION_JSON_TYPE = "application/json";
    var JAVASCRIPT_TYPE = "text/javascript";
    var CONTENT_TYPE = "Content-Type";
    var CHARSET = "utf-8";

    var isJsonContentType = function(type){
        if(!type){
            return false;
        }
        return type.substring(0,16).toLowerCase() == APPLICATION_JSON_TYPE;
    };

    edu.ajax = function (conf) {
        var xhr = new XMLHttpRequest();
        var defer = new edu.Defer();
        var method = conf.method || "GET";
        xhr.onreadystatechange = function () {
            if(xhr.readyState == 4){
                var result = {code:200,msg:"Success"};
                var status = xhr.status;
                if(status == 0){
                    result.code = 401;
                    result.msg = "ConnectFailed";
                }
                else if(status < 400){
                    var contentType = xhr.getResponseHeader(CONTENT_TYPE);
                    if(isJsonContentType(contentType)){
                        var json = JSON.parse(xhr.responseText);
                        if(json.code && json.msg){
                            result.code = json.code;
                            result.msg = json.msg;
                        }
                        else{
                            result.msg = json;
                        }
                    }
                    else{
                        result.msg = xhr.responseText;
                    }
                }
                else{
                    result.code = status;
                    result.msg = "ServerError";
                }

                if(result.code < 400){
                    defer.resolve(result);
                }
                else{
                    defer.reject(result);
                }
            }
        };
        var data = null;
        if(conf.data){
            data = JSON.stringify(conf.data);
        }
        else if(conf.formData){
            data = conf.formData;
        }
        xhr.open(method,conf.url);
        xhr.setRequestHeader('encoding',CHARSET);
        xhr.setRequestHeader("content-Type",APPLICATION_JSON_TYPE);
        xhr.send(data);
        return defer.promise;
    };

    this.edu = edu;
})(this);

