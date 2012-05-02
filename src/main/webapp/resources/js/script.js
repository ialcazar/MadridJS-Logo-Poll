var stepsForm = function(selector, options)
{
	selector = selector || document.body;
	
	this.$DOMscope =  $(selector);
	this.settings = {
		  stepsSelector: '.step'
		, currentStep: 0
		, messagesSelector: '#messages'
		, errorId: 'error'
		, errorTimeout: 5 //seconds
		, templateSufix: 'Tmpl'
		, stepFunctions: []
	};
	
	$.extend(true, this.settings, options);
	
	var _init = function(){
		this.steps = this.$DOMscope.find(this.settings.stepsSelector);
		this.currentStep = this.settings.currentStep;
		$.ajaxSetup({
			headers: {
				"Content-Type": "application/json",
				"Accept": "application/json"
			}
		});
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
			$step.get(0).stepFunctions = $.extend(true, {}, this.stepFunctions, this.settings.stepFunctions[step]);
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
			//stepFunctions.submitFormSuccess.apply(myThis, [{"id":"1","email":"israelalcazar@gmail.com"}]);
			$.ajax({
				  url: form_action
				, type: form_method
				, dataTypeString: 'json'
				, data: JSON.stringify($form.serializeObject())
				, beforeSend: function(){
					stepFunctions.submitFormBeforeSend.apply(myThis, toArray(arguments));
				}
				, success: function(){
					stepFunctions.submitFormSuccess.apply(myThis, toArray(arguments));
				}
				, error: function(){
					stepFunctions.submitFormError.apply(myThis, toArray(arguments));
				}
				, complete: function(){
					stepFunctions.submitFormComplete.apply(myThis, toArray(arguments));
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
			  , stepFunctions = $step[0].stepFunctions;
			if( step === this.currentStep )
			{
				var $getData = $step.find('[data-getdata]').each( function(index, element){
					stepFunctions.getData.apply(myThis, toArray(arguments));
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

var logoPollSettings = {
	currentStep: 0
};
logoPollSettings.stepFunctions = [];
logoPollSettings.stepFunctions[0] = {
	validForm: function($form){
		var $email = $form.find('#email');
		var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		if(reg.test($email.val()) === false) {
			return 'Escribe un email válido';
		}
		return true;
	}
	, submitFormSuccess: function(data){
		var $form = $(this.steps[this.currentStep+1]).find('form');
		$form.attr('action', $form.attr('action') + '/' + data.id );
		this.stepFunctions.submitFormSuccess.apply(this, toArray(arguments));
	}
};
logoPollSettings.stepFunctions[1] = {
	validForm: function(form){
		var votesChecked = $(form).find('[name="votes"]:checked');
		switch( votesChecked.length )
		{
			case 0:
				return 'No has seleccionado ningún logo';
			case 1:
				return true;
			default:
				return 'Has seleccionado más de 1 logo';
		}
	}
	, getData: function(index, element){
		var $layer = $(element)
		  , templateId = element.id + this.settings.templateSufix
		  , $step = this.steps.eq(this.currentStep)
		  , stepFunctions = $step[0].stepFunctions
		  , myThis = this;
		$('#' + templateId).template(templateId);
		
		data = {
			items: [
				  {
					  'id':14
				    , 'description': 'Logo 14'
				    , 'url': 'http://dl.dropbox.com/u/7490648/logos-MadridJS/14.png'
				  }
				,
				  {
					  'id':18
				    , 'description': 'Logo 18'
				    , 'url': 'http://dl.dropbox.com/u/7490648/logos-MadridJS/18.png'
				  }
			]
		};
		data.count = data.items.length;
		$.tmpl(templateId, data ).appendTo($layer);
		$mediaGrid = $layer.find('.media-grid');
		$mediaGrid.imagesLoaded(function(){
			$mediaGrid.masonry({
				// options
				itemSelector : 'li',
				columnWidth : 240
			});
		});
		$layer.find('img').twipsy();
		stepFunctions.afterSetStep.apply(this, [$step]);
	}
};
var toArray = function(obj) {
    return Array.prototype.slice.call(obj, 0);
};
window.LogoPoll = new stepsForm('body', logoPollSettings);