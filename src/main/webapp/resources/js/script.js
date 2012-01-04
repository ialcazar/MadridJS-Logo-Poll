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
			, stepsFunctions: []
		}
	};
	$.extend(true, sf.settings, options);
	
	var _init = function(){
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
				var data = { id: sf.settings.errorId };
				$.extend(true, data, request );
				sf.showError(data);
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
	sf.showError = function(data){
		var $errorContent = $.tmpl(sf.templateId, data).appendTo(sf.$messages.empty()).addClass('in');
		setTimeout(function(){
			$errorContent.alert('close');
		}, sf.settings.errorTimeout * 1000);
	};
	sf.submitForm = function(event){
		event.preventDefault();
		var form_action = this.action
		  , form_method = this.method
		  , stepsFunctions = $.extend(true, {}, sf.stepsFunctions, sf.settings.stepsFunctions[sf.currentStep])
		  , validForm = stepsFunctions.validForm();
		if( validForm === true )
		{
			sf.button = $(this).find('button').button();
			$.ajax({
				  url: form_action
				, type: form_method
				, data: $(this).serialize()
				, beforeSend: stepsFunctions.submitFormBeforeSend
				, success: stepsFunctions.submitFormSuccess
				, error: stepsFunctions.submitFormError
				, complete: stepsFunctions.submitFormComplete
			});
		}
		else
		{
			sf.showError({
				  id: 'errorStep' + sf.currentStep
				, errorMsg: validForm
			});
		}
	};
	
	sf.stepsFunctions = {};
	sf.stepsFunctions.validForm = function(){
		return true;
	};
	sf.stepsFunctions.submitFormBeforeSend = function(){
		sf.button.button('loading');
	};
	sf.stepsFunctions.submitFormComplete = function(){
		sf.button = null;
	};
	sf.stepsFunctions.submitFormError = function(){
		sf.button.button('reset');
	};
	sf.stepsFunctions.submitFormSuccess = function(data){
		sf.button.button('complete');
		sf.currentStep++;
		sf.steps.eq(sf.currentStep).find('[data-getdata]').each(function(){
			var $layer = $(this);
			var templateId = this.id + sf.settings.templateSufix;
			$('#' + templateId).template(templateId);
			
			data = {
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

var logoPollSettings = {};
logoPollSettings.stepsFunctions = [];
logoPollSettings.stepsFunctions[1] = {
	validForm: function(){
		var logosChecked = LogoPoll.steps.eq(LogoPoll.currentStep).find('[name="logo"]:checked');
		switch( logosChecked.length )
		{
			case 0:
				return 'No has seleccionado ningún logo';
			case 1: case 2: case 3:
				return true;
			default:
				return 'Has seleccionado más de 3 logos';
		}
	}
};

window.LogoPoll = new stepsForm('body', logoPollSettings);