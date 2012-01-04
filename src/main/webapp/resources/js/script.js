var stepsForm = function(selector, options)
{
	selector = selector || document.body;
	
	var sf = {
		  $DOMscope: $(selector)
		, settings: {
			  stepsSelector: '.step'
			, messagesSelector: '#messages'
			, errorId: 'error'
			, errorTimeout: 5 //seconds
			, templateSufix: 'Tmpl'
		}
	};

	var _init = function(){
		$.extend(true, sf.settings, options);
		sf.steps = sf.$DOMscope.find(sf.settings.stepsSelector);
		sf.currentStep = 0;
		_setErrorLayer();
		_bindEvents();
		sf.showCurrentStep();
		sf.$DOMscope.data('sf', sf);
	};
	var _setErrorLayer = function()
	{
		sf.templateId = sf.settings.errorId + sf.settings.templateSufix;
		$('#' + sf.templateId).template(sf.templateId);
		sf.$messages = sf.$DOMscope.find(sf.settings.messagesSelector).ajaxError(
			function(event, request, settings){
				var data = {
					id: sf.settings.errorId
				};
				$.extend(true, data, request );
				var $errorContent = $.tmpl(sf.templateId, data).appendTo(sf.$messages);
				setTimeout(function(){
					$errorContent.alert('close');
				}, sf.settings.errorTimeout * 1000);
			}
		);
	};
	var _bindEvents = function(){
		var step
		  , steps = sf.steps.length;
		
		for( step = 0; step < steps; step++)
		{
			sf.steps.eq(step).find('form').bind('submit', function(event){
				event.preventDefault();
				var form_action = this.action
				  , form_method = this.method;
				
				$.ajax({
					  url: form_action
					, type: form_method
					, data: $(this).serialize()
					, success: function(data, textStatus, jqXHR){
						console.log(data);
						console.log(textStatus);
						console.log(jqXHR);
						sf.currentStep++;
						sf.steps.eq(sf.currentStep).find('[data-getdata]').each(function(){
							var $layer = $(this);
							var templateId = this.id + sf.templateSufix;
							$('#' + templateId).template(templateId);
							$.ajax({
								  url: $layer.data('getdata')
								, dataType: 'json'
								, success: function(data, textStatus, jqXHR){
									$.tmpl(templateId, data ).appendTo($layer);
								},
								error: function(jqXHR, textStatus, errorThrown){
									sf.currentStep--;
									sf.showCurrentStep();
								}
							});
						});
						sf.showCurrentStep();
					}
				});
			});
		}	
	};
	sf.showCurrentStep = function(currentStep){
		sf.currentStep = currentStep || sf.currentStep;
		var step
		  , steps = sf.steps.length;
		
		for( step = 0; step < steps; step++)
		{
			if( step === sf.currentStep )
			{
				sf.showStep(step);
			}
			else
			{
				sf.hideStep(step);
			}
		}
	};
	sf.showStep = function(step){
		if( typeof step === 'number')
		{
			sf.steps.eq(step).show();
		}
	};
	sf.hideStep = function(step){
		if( typeof step === 'number')
		{
			sf.steps.eq(step).hide();
		}
	};
	_init();
	return sf;
};

window.LogoPoll = new stepsForm();