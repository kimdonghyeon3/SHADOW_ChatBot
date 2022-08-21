//
// !function(e,n){
//     var t=document.createElement("div");
//
//     t.id=e,t.className=e,document.body.appendChild(t);
//
//     var o, t=navigator.userAgent;
//
//     (-1<t.indexOf("MSIE ")||-1<t.indexOf("Trident")) &&
//     ((t=document.createElement("script")).src="https://polyfill.io/v3/polyfill.min.js?features=Element.prototype.append",document.body.insertBefore(t,null))
//         ,["static/css/2.6bc04f7a.chunk.css"]
//         .map(function(e){
//             var t=document.createElement("link");
//             t.href=n+e,t.rel="stylesheet",document.head.insertBefore(t,null)
//         }),["static/js/runtime-main.4a5a4287.js","static/js/2.734e5860.chunk.js","static/js/main.3e0dd827.chunk.js"]
//         .map(function(e){
//             var t=document.createElement("script");
//             t.src=n+e,document.body.insertBefore(t,null)}),
//             dyc.chatbotUid&&(
//                 (o=new XMLHttpRequest).open("get",n+"api/chatbots/"+dyc.chatbotUid+"/popup"),
//                 o.setRequestHeader("Content-Type","application/json"),
//                     o.onreadystatechange=function(){
//                         4===o.readyState&&200===o.status&&JSON.parse(o.responseText).popup
//                             .map(function(e){
//                                 var t=document.createElement("a");t.href=e.targetUrl,t.pathname==location.pathname&&e.buttons
//                                     .map(function(e){
//                                         var t=[{textColor:e.textColor,closeColor:e.closeColor,backgroundColor:e.backgroundColor},e.description,{intentUid:e.eventId}];
//                                         setTimeout(function(){var e;(e=dyc).actionPopup.apply(e,t)},1e3*e.startTime)})
//
//                             })},o.send())
//
// }("dychat-20210908","https://cloudturing.chat/v1.0/");