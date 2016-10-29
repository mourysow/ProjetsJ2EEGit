if (typeof jQuery === "undefined") {
  throw new Error("PushJS requires jQuery");
}

var PushJS = {
	/**
	 * Permet d'envoyer les informations d'un téléphone nécessaires pour le push (uniquement pour le test)
	 */
	sendPushRegistration : function(){

	    var form=	$('#pushTestForm');
		$.ajax({
			url: form.attr('action'),
			data : form.serialize(),
			type : 'post',
			error : this._onErrorSendMessage,
			success : this._onSuccessSendMessage
			});
	},
	
	_onErrorSendMessage : function (jqXHR, textStatus, errorThrown){

		if (jqXHR.status && jqXHR.status == 403) {
			$('body').trigger("timeout");
		} else {
			$('body').trigger("error", errorThrown);	
		}
	},
	
	_onSuccessSendMessage : function (data, textStatus, jqXHR){

		alert(data);
		$('body').trigger("success");

		
	}
	
};