
var flashvars = {};
flashvars.mode = 'tags';
flashvars.minFontSize = '8';  /* минимальный размер  шрифта в облаке. Размер задается в пунктах.*/
flashvars.maxFontSize = '12';  /* максимальный размер  шрифта в облаке. Размер задается в пунктах.*/
flashvars.tcolor = '0x006600';  /*"0x006600" - цвет самого частого текста в облаке тегов в RGB формате. Частота текста определяется по свойству ссылки style. 0x не меняем.*/
flashvars.tcolor2 = '0x006600'; /* "0x006600" - цвет самого редкого текста в облаке тегов в RGB формате. Частота текста определяется по свойству ссылки style. 0x не меняем.*/
flashvars.hicolor = '0xFF0000';   /*"0xFF0000" - цвет текста при наведении на него курсора. 0x не меняем.*/
flashvars.distr = 'true';   /*тип вращения облака, может принимать значение "true" или "false"*/
flashvars.tspeed = '100'; /*скорость вращения*/
eTagz = document.getElementById('cloud').getElementsByTagName('A');
flashvars.tagcloud = '<tags>';
for (var i=0; eTagz[i]; ++i) flashvars.tagcloud += '<a href=\'' + eTagz[i].getAttribute('href')
+ '\' style=\'' + parseInt(eTagz[i].style.fontSize)
+ '\'>' + eTagz[i].innerHTML.replace(/&/i, '%26') + '</a>';
delete eTagz;
flashvars.tagcloud += '</tags>';
var params = {};
/*params.wmode = 'transparent';  устанавливаем фон облака прозрачным. Если убрать эту строку, то можно будет самим задавать цвет фона.*/
params.bgcolor = '#FFFFCC';
params.allowscriptaccess = 'always';
var attributes = {};
attributes.id = 'yoblako';
attributes.name = 'tagcloud';
swfobject.embedSWF('http://neotech.3dn.ru/Tegs/tagcloud.swf', 'yoblako', '300', '200', '9.0.0', false, flashvars, params, attributes);
/*"300" - ширина будущего облака тегов в пикселях.
"200" - высота будущего облака тегов.
"9" - максимальная версия флеш-плеера, которая нужна для отображения облака.*/



