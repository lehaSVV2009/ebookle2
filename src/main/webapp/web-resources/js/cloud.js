var flashvars = {};
flashvars.mode = 'tags';
flashvars.minFontSize = '8';
flashvars.maxFontSize = '12';
flashvars.tcolor = '0x006600';
flashvars.tcolor2 = '0x006600';
flashvars.hicolor = '0xFF0000';
flashvars.distr = 'true';
flashvars.tspeed = '100';
eTagz = document.getElementById('yoblako').getElementsByTagName('A');
flashvars.tagcloud = '<tags>';
for (var i=0; eTagz[i]; ++i) flashvars.tagcloud += '<a href=\'' + eTagz[i].getAttribute('href')
    + '\' style=\'' + parseInt(eTagz[i].style.fontSize)
    + '\'>' + eTagz[i].innerHTML.replace(/&/i, '%26') + '</a>';
delete eTagz;
flashvars.tagcloud += '</tags>';
var params = {};
params.wmode = 'transparent';
params.allowscriptaccess = 'always';
var attributes = {};
attributes.id = 'yoblako';
attributes.name = 'tagcloud';
swfobject.embedSWF('http://neotech.3dn.ru/Tegs/tagcloud.swf', 'yoblako', '217', '200', '9.0.0', false, flashvars, params, attributes);
