$(function() {
	var configMask = {
		decimal : ',',
		thousands : '.'
	};
	
	$('.js-decimal').maskMoney(configMask);
        
        // acrescento mais um atributo ao objeto
	configMask.precision = 0;
	
	$('.js-plain').maskMoney(configMask);
});
