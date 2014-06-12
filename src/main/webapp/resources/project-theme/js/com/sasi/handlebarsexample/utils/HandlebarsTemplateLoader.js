define(["jquery"], function($)
{
	//logger.debug("*** HandlebarsTemplateLoader ***");
	//logger.debug("jquery: ", $);
	"use strict";

	var HandlebarsTemplateLoader = function()
	{
		return {
			templates: {},

			loadTemplates: function(names, callback)
			{
				var that = this;

				var loadTemplate = function(index) {
					var name = names[index];
					logger.debug('HandlebarsTemplateLoader::loadTemplate, name: ' + name);
					$.get(name, function(data, textStatus, jqXHR) {
						that.templates[name] = jqXHR.responseText;
						index++;
						if (index < names.length) {
							loadTemplate(index);
						} else {
							callback();
						}
					});
				}

				loadTemplate(0);
			},

			get: function(name) {
				return this.templates[name];
			}
		};
	};

	return HandlebarsTemplateLoader;
});