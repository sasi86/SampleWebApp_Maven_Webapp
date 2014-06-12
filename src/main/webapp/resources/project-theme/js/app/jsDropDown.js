define(["hbs!com/sasi/handlebarsexample/templates/country",
        "hbs!com/sasi/handlebarsexample/templates/state",
        "hbs!com/sasi/handlebarsexample/templates/city"], function(country,state,city){
	
	var hbsTemplates = {"country": country,"state" : state,"city" : city};
	var logger;
	var jsDropDown = {
		_init : function(){
			logger = log4javascript.getLogger("app.js");
			logger.setLevel(log4javascript.Level.ALL);
			logger.addAppender(new log4javascript.BrowserConsoleAppender());
			window.logger = logger;
			logger.debug("app::init method");
			return this;
		},
	
		_loadData : function(reqMethod, URL, containerId,template){
			$.ajax({
				type : reqMethod,
				url : URL, 
				success : function(data) {
					logger.debug("app::_loadData method");
					var html = hbsTemplates[template](data);
				    $(containerId).html (html);
				}
			});
			return this;
		},
		
		_listen : function(){
			$('#country').live('change', function() {
				jsDropDown._loadData("GET",  "/SampleWebApp/mvc/chooseCity/get/state?id="+$(this).val(), "#StateDiv","state");
			});
			$('#State').live('change', function() {
				jsDropDown._loadData("GET",  "/SampleWebApp/mvc/chooseCity/get/city?id="+ $(this).val(), "#CityDiv","city");
			});
			return this;
		},
		_start : function(){
			this._init()._loadData("GET", "/SampleWebApp/mvc/chooseCity/handleBar", "#CountryDiv", "country")._listen();
			return this;
		}
	};
	
	return {start : jsDropDown._start()};
	
});
