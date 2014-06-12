/// utilities
(function($) {
	
	/// :text patterns
	var
	$msg = $('#msgBox'),
	_busy = 0,
	$busy = $('#busyBox'),
	_info = 0,
	$info = $('#infoBox'),
	PATTERN = {};
	
	$.map( {
		'num'		: '0123456789',
		'alphanum'	: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 _-.,'
	}, function( val, key ) {
		/// default to allow backspace, delete, tab, escape, enter
		var map = [0, 46, 8, 9, 27, 13];
		for(var i=0, l=val.length; i<l; i++){
			map.push( val.charCodeAt(i) );
		}
		PATTERN[key] = map;
	});
	//console.log( PATTERN );
	
	function handleSuccess( response, callback, dataType ) {
		
		if( dataType=='html' ) {
			callback.apply( this, arguments );
		} else {
			/// JSON
			//console.log( response );
			
			if( response.status==0 ) {
				callback.apply( this, arguments );
			} else {
				var status = response.status;
				
				switch (status) {
				case 1:
					app.store.isIE && $('#dialog').modal('hide');
					
					$msg.modal('show')
					.find('h3').text('Error Occured!').end()
					.find('p').html(
						'<span class="badge badge-important">status: ' + status + '</span> '
						+ ( response.msg || response.message )
					).end();
				break;
				
				case 10:
					alert('error occured: status=' + response.status +' msg=' + response.msg );
					location.href = 'index.jsp'; //TODO
				break;
				
				default:
					//TODO more status, use switch status
					alert('error occured: status=' + response.status +' msg=' + response.msg );
				break;
				}
			}
		}
		
		if(!--_busy) $busy.addClass('hide');
	}
	
	function handleError(url, err) {
		//alert('ERRORee\n'+ err.status +' : '+ err.statusText);
		//console.log('ERROR', err);
		
		var description = $('<div><p class="text-error">'+ url +'</p><span class="badge badge-important">' + err.status + '</span> '+ err.statusText + '</div>'),
		responseHTML = $.parseHTML(err.responseText);
		//console.log( responseHTML );
		
		description.append('<hr>');
		$.each( responseHTML, function( i, el ) {
			//nodeNames[i] = "<li>" + el.nodeName + "</li>";
			//console.log( el.nodeName, el );
			if( "title h1 p pre hr".indexOf( el.nodeName.toLowerCase() )>-1 ) {
				if( el.nodeName.toLowerCase()=="title" ) el = "<h3>"+ el.innerHTML + "</h3>";
				else if( el.nodeName.toLowerCase()=="h1" ) el = "<h4>"+ el.innerHTML + "</h4>";
				description.append( el );
			}
		});
		
		app.store.isIE && $('#dialog').modal('hide');
		
		$('#msgBox').modal('show')
		.find('h3').text('Error Occured!').end()
		.find('p').html(description).end()
		;
		
		if( app.devel ) {
			$.develLoad({ data:err.status +' : '+ err.statusText });
		}
		
		if(!--_busy) $busy.addClass('hide');
	}
	
	/// shorthand - post JSON and receive JSON/HTML
	/// test loadHTML, add dataType
	$.each( [ 'loadData', 'loadHTML' ], function( i, fn ) {
		$[ fn ] = function( url, data, callback, dataType ) {
			/// shift arguments if data argument was omitted!
			if ( $.isFunction( data ) ) {
				dataType = dataType || callback;
				callback = data;
				data = undefined;
			} else {
				data = JSON.stringify(data);
			}
			
			if( dataType === undefined && fn=='loadHTML' ) dataType = 'html';
			
			//devel console.log(1, data, dataType, fn, (dataType=='html' ? 'text/html' : 'application/json'));
			if( app.devel ) {
				var _callback_copy = callback;
				callback = function(data) {
					_callback_copy(data);
					$.develLoad({ data:data });
				};
				$.develLoad({ url:url, param:data, dataType:dataType });
			}
			
			if(!_busy) $busy.removeClass('hide');
			_busy++;
			
			return $.ajax({
				beforeSend: function(xhrObj){
					xhrObj.setRequestHeader('Content-Type', 'application/json');
					xhrObj.setRequestHeader('Accept', (dataType=='html' ? 'text/html' : 'application/json'));
				},
				type: 'POST',
				cache: false,
				dataType: dataType,
				url: url,
				data: data,
				success: function(response) {
					handleSuccess(response, callback, dataType);
				},
				error: function(err) {
					handleError(url, err);
				}
			});
		};
	});
	
	/// to int
	$.pi = function(no) {
		no = parseInt(no, 10);
		return isNaN(no) ? 0 : no;
	};
	
	
	/// to int
	$.mapOptions = function( arr ) {
		return $.map( arr, function( a ) {
			return '<option value="'+ a.value +'">'+ a.label +'</option>';
		} );
	};
	
	
	/// param
	$.extendParam = function( param, _param, deep ) {
		return $.extend(deep || false, {}, param, _param);
	};
	
	
	/// get txts for lang inside this $body
	$.txt = function( $body ) {
		var TXT = {}, $t;
		$body.find('.txt > div').each(function(){
			$t = $(this);
			TXT[ $t.data('id') ] = $t.html();
		});
		$body.find('.txt').remove();
		return TXT;
	};
	
	///render template
	$.templ = function( templ, ob ) {
		///hay.replace(new RegExp(needle, 'g'), replace);
		//return templ;
		
		$.map( ob, function( val, key ) {
			templ = templ.replace(new RegExp('{'+ key +'}', 'g'), val);
			
			//console.log( val, ':', key, ':', new RegExp('{'+ key +'}', 'g') );
			//console.log( templ );
		});
		
		//console.log( templ );
		return templ;
	};
	
	$.focus = function( a, t ) {
		setTimeout( function(){ a.focus(); }, t || 100 );
	};
	
	$.templRepeat = function( templ, obArr, joiner ) {
		return $.map( obArr, function(item){
			return $.templ( templ, item );
		}).join(joiner || '');
	};
	
	/// info box
	$.info = function( txt ) {
		if( txt ) {
			_info++;
			$info.removeClass('hide').text(txt);
			setTimeout( function(){
				--_info || $info.addClass('hide');
			}, 5000 );
		}
	};
	
	$.fn.extend({
	check: function() {
		return this.each(function() { this.checked = true; });
	},
	txt: function() { // trim
		var val = $.trim($(this).val());
		$(this).val(val);
		return val;
	},
	numVal: function() {
		return $.pi( $(this).txt() );
	},
	pattern: function() {
		//todo restrict on copy+c
		$(this).find(':text[data-pattern], textarea[data-pattern]').keypress(function(e){
			
			var key = e.charCode, allowed = PATTERN[$(this).data('pattern')];
			if( allowed.indexOf(key) == -1 ) return false;
		});
		
		app.store.isIE && $(this).find('textarea[maxlength]').keypress(function(e){
			var $t = $(this);
			if( $t.val().length > $t.attr('maxlength') ) return false;
		});
	}
	});
	
	
	/// console for ie9
	try{
		console;
	} catch(e) {
		window.console = {
			log: function(arg) {
				//alert( arguments.join("\n") );
			}
		};
	}
	
})(window.$);

