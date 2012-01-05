var stepsForm = function(selector, options)
{
	selector = selector || document.body;
	
	this.$DOMscope =  $(selector);
	this.settings = {
		  stepsSelector: '.step'
		, messagesSelector: '#messages'
		, errorId: 'error'
		, errorTimeout: 5 //seconds
		, templateSufix: 'Tmpl'
		, stepFunctions: []
	};
	
	$.extend(true, this.settings, options);
	
	var _init = function(){
		this.steps = this.$DOMscope.find(this.settings.stepsSelector);
		this.currentStep = 0;
		_setErrorLayer.call(this);
		_setSteps.call(this);
		this.showCurrentStep();
		this.$DOMscope.data('sf', this);
	};
	var _setErrorLayer = function()
	{
		var myThis = this;
		this.templateId = this.settings.errorId + this.settings.templateSufix;
		$('#' + this.templateId).template(this.templateId);
		this.$messages = this.$DOMscope.find(this.settings.messagesSelector).ajaxError(
			function(event, request, settings){
				var data = { id: myThis.settings.errorId };
				$.extend(true, data, request );
				myThis.showError(data);
			}
		);
	};
	var _setSteps = function(){
		var step
		  , steps = this.steps.length
		  , myThis = this;
		
		for( step = 0; step < steps; step++)
		{
			var $step = this.steps.eq(step);
			$step[0].stepFunctions = $.extend(true, {}, this.stepFunctions, this.settings.stepFunctions[step]);
			$step.find('form').bind('submit', function(event){
				myThis.submitForm(event);
			});
			
		}	
	};
	this.showError = function(data){
		var $errorContent = $.tmpl(this.templateId, data).appendTo(this.$messages.empty()).addClass('in');
		setTimeout(function(){
			$errorContent.alert('close');
		}, this.settings.errorTimeout * 1000);
	};
	this.submitForm = function(event){
		event.preventDefault();
		var form = event.currentTarget
		  , $form = $(form)
		  , form_action = form.action
		  , form_method = form.method
		  , stepFunctions = this.steps[this.currentStep].stepFunctions
		  , validForm = stepFunctions.validForm.apply(this, [$form])
		  , myThis = this;
		if( validForm === true )
		{
			this.button = $form.find('[type="submit"]').button();
			$.ajax({
				  url: form_action
				, type: form_method
				, dataTypeString: 'json'
				, data: $form.serialize()
				, beforeSend: function(){
					stepFunctions.submitFormBeforeSend.apply(myThis, arguments.callee.arguments);
				}
				, success: function(){
					stepFunctions.submitFormSuccess.apply(myThis, arguments.callee.arguments);
				}
				, error: function(){
					stepFunctions.submitFormError.apply(myThis, arguments.callee.arguments);
				}
				, complete: function(){
					stepFunctions.submitFormComplete.apply(myThis, arguments.callee.arguments);
				}
			});
		}
		else
		{
			this.showError({
				  id: 'errorStep' + this.currentStep
				, errorMsg: validForm
			});
		}
	};
	
	this.stepFunctions = {};
	this.stepFunctions.validForm = function($form){
		return true;
	};
	this.stepFunctions.submitFormBeforeSend = function(){
		this.button.button('loading');
	};
	this.stepFunctions.submitFormComplete = function(){
		this.button = null;
	};
	this.stepFunctions.submitFormError = function(){
		this.button.button('reset');
	};
	this.stepFunctions.submitFormSuccess = function(data){
		this.button.button('complete');
		this.currentStep++;
		var $step = this.steps.eq(this.currentStep)
		  , stepFunctions = $step[0].stepFunctions
		  , myThis = this;
		this.showCurrentStep();
	};
	
	this.stepFunctions.getData = function(index, element){
		var $layer = $(element)
		  , templateId = element.id + this.settings.templateSufix
		  , $step = this.steps.eq(this.currentStep)
		  , stepFunctions = $step[0].stepFunctions
		  , myThis = this;
		$('#' + templateId).template(templateId);
		$.ajax({
			  url: $layer.data('getdata')
			, dataType: 'json'
			, success: function(data, textStatus, jqXHR){
				$.tmpl(templateId, data ).appendTo($layer);
				stepFunctions.afterSetStep.apply(myThis, [$step]);
			},
			error: function(jqXHR, textStatus, errorThrown){
				this.currentStep--;
				this.showCurrentStep();
			}
		});
	};
	this.stepFunctions.afterSetStep = function(){};
	
	
	this.showCurrentStep = function(currentStep){
		this.currentStep = currentStep || this.currentStep;
		var step
		  , steps = this.steps.length
		  , myThis = this;
		
		for( step = 0; step < steps; step++)
		{
			var $step = this.steps.eq(step)
			  , stepFunctions = $step[0].stepFunctions
			if( step === this.currentStep )
			{
				var $getData = $step.find('[data-getdata]').each( function(index, element){
					stepFunctions.getData.apply(myThis, arguments.callee.arguments);
				});
				this.showStep(step);
			}
			else
			{
				this.hideStep(step);
			}
		}
	};
	this.showStep = function(step){
		if( typeof step === 'number')
		{
			this.steps.eq(step).show();
		}
	};
	this.hideStep = function(step){
		if( typeof step === 'number')
		{
			this.steps.eq(step).hide();
		}
	};
	_init.call(this);
	return this;
};

var logoPollSettings = {};
logoPollSettings.stepFunctions = [];
logoPollSettings.stepFunctions[0] = {
	validForm: function($form){
		var $email = $form.find('#email');
		this.email = $email.attr('value');
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if(reg.test(this.email) === false) {
			return 'Escribe un email válido';
		}
		return true;
	}
};
logoPollSettings.stepFunctions[1] = {
	validForm: function(form){
		var logosChecked = $(form).find('[name="logo"]:checked');
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
	, afterSetStep: function($step) {
		var $form = $step.find('form');
		$form.attr('action', $form.attr('action') + '/' + this.email );
	}
	, getData: function(index, element){
		var $layer = $(element)
		  , templateId = element.id + this.settings.templateSufix
		  , $step = this.steps.eq(this.currentStep)
		  , stepFunctions = $step[0].stepFunctions
		  , myThis = this;
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
		$layer.find('img').twipsy();
		stepFunctions.afterSetStep.apply(this, [$step]);
	}
};

window.LogoPoll = new stepsForm('body', logoPollSettings);