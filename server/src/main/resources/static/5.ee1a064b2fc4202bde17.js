(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{TDBs:function(t,n,e){"use strict";e.r(n),e.d(n,"DashboardModule",function(){return v});var o=e("tyNb"),a=e("ofXK"),r=e("YUcS"),b=e("5+ba"),i=e("fXoL"),c=e("AytR"),l=e("tk/3");let d=(()=>{class t{constructor(t){this.httpClient=t,this.url=c.a.apiUrl}getDetails(){return this.httpClient.get(this.url+"/dashboard/details")}}return t.\u0275fac=function(n){return new(n||t)(i.Yb(l.b))},t.\u0275prov=i.Kb({token:t,factory:t.\u0275fac,providedIn:"root"}),t})();var s=e("p20J"),u=e("Wp6s");const p=function(){return["/cafe/category"]},g=function(){return["/cafe/product"]},h=function(){return["/cafe/bill"]},f=function(){return["/cafe/table"]},T=[{path:"",component:(()=>{class t{constructor(t,n){this.dashboardService=t,this.snackBarService=n,this.dashboardData()}ngAfterViewInit(){}dashboardData(){this.dashboardService.getDetails().subscribe(t=>{this.data=t},t=>{var n,e;console.log(t),this.reponseMessage=(null===(n=t.error)||void 0===n?void 0:n.message)?null===(e=t.error)||void 0===e?void 0:e.message:b.a.genericError,this.snackBarService.openSnackBar(this.reponseMessage,b.a.error)})}}return t.\u0275fac=function(n){return new(n||t)(i.Ob(d),i.Ob(s.a))},t.\u0275cmp=i.Ib({type:t,selectors:[["app-dashboard"]],decls:47,vars:12,consts:[[1,"header"],[1,"row"],[1,"column"],[1,"card"],[1,"container"],[1,"title"],[1,"button",3,"routerLink"]],template:function(t,n){1&t&&(i.Ub(0,"body"),i.Ub(1,"mat-card"),i.Ub(2,"b"),i.Ub(3,"span",0),i.Ac(4,"Dashboard"),i.Tb(),i.Tb(),i.Tb(),i.Pb(5,"br"),i.Ub(6,"div",1),i.Ub(7,"div",2),i.Ub(8,"div",3),i.Ub(9,"div",4),i.Ub(10,"h2",5),i.Ac(11,"Total Category:"),i.Tb(),i.Ub(12,"h1",5),i.Ac(13),i.Tb(),i.Ub(14,"p"),i.Ub(15,"button",6),i.Ac(16,"View Category"),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Ub(17,"div",2),i.Ub(18,"div",3),i.Ub(19,"div",4),i.Ub(20,"h2",5),i.Ac(21,"Total Product:"),i.Tb(),i.Ub(22,"h1",5),i.Ac(23),i.Tb(),i.Ub(24,"p"),i.Ub(25,"button",6),i.Ac(26,"View Product"),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Ub(27,"div",2),i.Ub(28,"div",3),i.Ub(29,"div",4),i.Ub(30,"h2",5),i.Ac(31,"Total Bill:"),i.Tb(),i.Ub(32,"h1",5),i.Ac(33),i.Tb(),i.Ub(34,"p"),i.Ub(35,"button",6),i.Ac(36,"View Bill"),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Ub(37,"div",2),i.Ub(38,"div",3),i.Ub(39,"div",4),i.Ub(40,"h2",5),i.Ac(41,"Total Table:"),i.Tb(),i.Ub(42,"h1",5),i.Ac(43),i.Tb(),i.Ub(44,"p"),i.Ub(45,"button",6),i.Ac(46,"View Table"),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb(),i.Tb()),2&t&&(i.Db(13),i.Bc(null==n.data?null:n.data.category),i.Db(2),i.lc("routerLink",i.nc(8,p)),i.Db(8),i.Bc(null==n.data?null:n.data.product),i.Db(2),i.lc("routerLink",i.nc(9,g)),i.Db(8),i.Bc(null==n.data?null:n.data.bill),i.Db(2),i.lc("routerLink",i.nc(10,h)),i.Db(8),i.Bc(null==n.data?null:n.data.table),i.Db(2),i.lc("routerLink",i.nc(11,f)))},directives:[u.a,o.d],styles:['.position-relative[_ngcontent-%COMP%]{position:relative}.add-contact[_ngcontent-%COMP%]{position:absolute;right:17px;top:57px}body[_ngcontent-%COMP%]{font-family:Arial,Helvetica,sans-serif;margin:0}html[_ngcontent-%COMP%]{box-sizing:border-box}*[_ngcontent-%COMP%], [_ngcontent-%COMP%]:after, [_ngcontent-%COMP%]:before{box-sizing:inherit}.column[_ngcontent-%COMP%]{float:left;width:33.3%;margin-bottom:16px;padding:0 8px}.card[_ngcontent-%COMP%]{box-shadow:0 4px 8px 0 #0003;margin:8px;font-family:Lucida Handwriting}.about-section[_ngcontent-%COMP%]{padding:50px;text-align:center;background-color:#474e5d;color:#fff}.container[_ngcontent-%COMP%]{padding:0 16px}.container[_ngcontent-%COMP%]:after, .row[_ngcontent-%COMP%]:after{content:"";clear:both;display:table}.title[_ngcontent-%COMP%]{color:#000;text-align:center!important}.button[_ngcontent-%COMP%]{border:none;outline:0;display:inline-block;padding:8px;color:#fff;background-color:#7b1fa2;text-align:center;cursor:pointer;width:100%;font-weight:700}.button[_ngcontent-%COMP%]:hover{background-color:#555}@media screen and (max-width:650px){.column[_ngcontent-%COMP%]{width:100%;display:block}}.header[_ngcontent-%COMP%]{font-family:Lucida Handwriting}']}),t})()}];var U=e("qAUw");let v=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=i.Mb({type:t}),t.\u0275inj=i.Lb({imports:[[a.c,U.a,r.a,o.g.forChild(T)]]}),t})()}}]);