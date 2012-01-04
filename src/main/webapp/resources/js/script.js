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
				var $errorContent = $.tmpl(sf.templateId, data).appendTo(sf.$messages.empty()).addClass('in');
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
			sf.steps.eq(step).find('form').bind('submit', sf.submitForm);
		}	
	};
	sf.submitForm = function(event){
		event.preventDefault();
		var form_action = this.action
		  , form_method = this.method;
		
		sf.button = $(this).find('button').button();
		$.ajax({
			  url: form_action
			, type: form_method
			, data: $(this).serialize()
			, beforeSend: sf.submitFormBeforeSend
			, success: sf.submitFormSuccess
			, error: sf.submitFormError
			, complete: sf.submitFormComplete
		});
	};
	sf.submitFormBeforeSend = function(data, textStatus, jqXHR){
		sf.button.button('loading');
	};
	sf.submitFormComplete = function(data, textStatus, jqXHR){
		sf.button = null;
	};
	sf.submitFormError = function(data, textStatus, jqXHR){
		sf.button.button('reset');
	};
	sf.submitFormSuccess = function(data, textStatus, jqXHR){
		sf.button.button('complete');
		sf.currentStep++;
		sf.steps.eq(sf.currentStep).find('[data-getdata]').each(function(){
			var $layer = $(this);
			var templateId = this.id + sf.settings.templateSufix;
			$('#' + templateId).template(templateId);
			
			var data = {
				  'count': 10
				, items: [
					  {
						  'id':1
						, 'description': 'Vestibulum nisi sapien, laoreet vitae iaculis ut, facilisis at ante. Integer interdum laoreet est, id porta turpis dictum sed. Aenean.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':2
						, 'description': 'Aliquam eu mi massa, eget luctus sem. In sollicitudin aliquam nisi sed dictum. Aenean lobortis faucibus libero eget mollis. Etiam.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':3
						, 'description': 'Sed hendrerit tellus quis augue dictum lobortis. Donec mollis eleifend dui vel laoreet. Aliquam rhoncus malesuada rutrum. Mauris est ligula.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':4
						, 'description': 'Cras id augue a leo convallis placerat. Proin ultricies lacinia tempus. Nullam facilisis tincidunt dui, nec blandit libero imperdiet vitae.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':5
						, 'description': 'Suspendisse est lectus, mattis at aliquam ut, luctus porta dolor. Ut enim dui, aliquam eu scelerisque at, iaculis id arcu.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':6
						, 'description': 'Fusce mattis lacus at purus lobortis a tempus turpis auctor. Donec iaculis feugiat tellus, eu dignissim libero pretium eu. Donec.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':7
						, 'description': 'Etiam volutpat suscipit nunc, at eleifend nunc tincidunt at. Donec eget nisi id justo fringilla bibendum eu rutrum lectus. Cras.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':8
						, 'description': 'Aenean vestibulum, turpis et sagittis euismod, sapien dolor elementum felis, a fringilla nibh mi non neque. Sed viverra velit sed.'
						, 'url': 'http://placehold.it/90x90'
					  }
					, {
						  'id':9
						, 'description': 'Sed elit odio, molestie non condimentum non, eleifend a felis. Nam ut nibh hendrerit nulla vestibulum sollicitudin. Morbi commodo purus.'
						, 'url': 'http://placehold.it/90x90'
					  }
				]
			};
			$.tmpl(templateId, data ).appendTo($layer);
			
			// $.ajax({
			// 	  url: $layer.data('getdata')
			// 	, dataType: 'json'
			// 	, success: function(data, textStatus, jqXHR){
			// 		$.tmpl(templateId, data ).appendTo($layer);
			// 	},
			// 	error: function(jqXHR, textStatus, errorThrown){
			// 		sf.currentStep--;
			// 		sf.showCurrentStep();
			// 	}
			// });
		});
		sf.showCurrentStep();
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