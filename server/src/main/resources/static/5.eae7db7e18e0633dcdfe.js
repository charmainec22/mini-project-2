(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{TDBs:function(t,n,o){"use strict";o.r(n),o.d(n,"DashboardModule",function(){return T});var e=o("tyNb"),r=o("ofXK"),a=o("YUcS"),i=o("5+ba"),c=o("fXoL"),b=o("AytR"),d=o("tk/3");let l=(()=>{class t{constructor(t){this.httpClient=t,this.url=b.a.apiUrl}getDetails(){return this.httpClient.get(this.url+"/dashboard/details")}}return t.\u0275fac=function(n){return new(n||t)(c.Yb(d.b))},t.\u0275prov=c.Kb({token:t,factory:t.\u0275fac,providedIn:"root"}),t})();var s=o("p20J"),u=o("Wp6s");const p=function(){return["/cafe/category"]},g=function(){return["/cafe/product"]},h=function(){return["/cafe/bill"]},f=[{path:"",component:(()=>{class t{constructor(t,n){this.dashboardService=t,this.snackBarService=n,this.dashboardData()}ngAfterViewInit(){}dashboardData(){this.dashboardService.getDetails().subscribe(t=>{this.data=t},t=>{var n,o;console.log(t),this.reponseMessage=(null===(n=t.error)||void 0===n?void 0:n.message)?null===(o=t.error)||void 0===o?void 0:o.message:i.a.genericError,this.snackBarService.openSnackBar(this.reponseMessage,i.a.error)})}}return t.\u0275fac=function(n){return new(n||t)(c.Ob(l),c.Ob(s.a))},t.\u0275cmp=c.Ib({type:t,selectors:[["app-dashboard"]],decls:37,vars:9,consts:[[1,"header"],[1,"row"],[1,"column"],[1,"card"],[1,"container"],[1,"title"],[1,"button",3,"routerLink"]],template:function(t,n){1&t&&(c.Ub(0,"body"),c.Ub(1,"mat-card"),c.Ub(2,"b"),c.Ub(3,"span",0),c.Ac(4,"Dashboard"),c.Tb(),c.Tb(),c.Tb(),c.Pb(5,"br"),c.Ub(6,"div",1),c.Ub(7,"div",2),c.Ub(8,"div",3),c.Ub(9,"div",4),c.Ub(10,"h2",5),c.Ac(11,"Total Category:"),c.Tb(),c.Ub(12,"h1",5),c.Ac(13),c.Tb(),c.Ub(14,"p"),c.Ub(15,"button",6),c.Ac(16,"View Category"),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Ub(17,"div",2),c.Ub(18,"div",3),c.Ub(19,"div",4),c.Ub(20,"h2",5),c.Ac(21,"Total Product:"),c.Tb(),c.Ub(22,"h1",5),c.Ac(23),c.Tb(),c.Ub(24,"p"),c.Ub(25,"button",6),c.Ac(26,"View Product"),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Ub(27,"div",2),c.Ub(28,"div",3),c.Ub(29,"div",4),c.Ub(30,"h2",5),c.Ac(31,"Total Bill:"),c.Tb(),c.Ub(32,"h1",5),c.Ac(33),c.Tb(),c.Ub(34,"p"),c.Ub(35,"button",6),c.Ac(36,"View Bill"),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Tb(),c.Tb()),2&t&&(c.Db(13),c.Bc(null==n.data?null:n.data.category),c.Db(2),c.lc("routerLink",c.nc(6,p)),c.Db(8),c.Bc(null==n.data?null:n.data.product),c.Db(2),c.lc("routerLink",c.nc(7,g)),c.Db(8),c.Bc(null==n.data?null:n.data.bill),c.Db(2),c.lc("routerLink",c.nc(8,h)))},directives:[u.a,e.d],styles:['.position-relative[_ngcontent-%COMP%]{position:relative}.add-contact[_ngcontent-%COMP%]{position:absolute;right:17px;top:57px}body[_ngcontent-%COMP%]{font-family:Arial,Helvetica,sans-serif;margin:0}html[_ngcontent-%COMP%]{box-sizing:border-box}*[_ngcontent-%COMP%], [_ngcontent-%COMP%]:after, [_ngcontent-%COMP%]:before{box-sizing:inherit}.column[_ngcontent-%COMP%]{float:left;width:33.3%;margin-bottom:16px;padding:0 8px}.card[_ngcontent-%COMP%]{box-shadow:0 4px 8px 0 #0003;margin:8px;font-family:Lucida Handwriting}.about-section[_ngcontent-%COMP%]{padding:50px;text-align:center;background-color:#474e5d;color:#fff}.container[_ngcontent-%COMP%]{padding:0 16px}.container[_ngcontent-%COMP%]:after, .row[_ngcontent-%COMP%]:after{content:"";clear:both;display:table}.title[_ngcontent-%COMP%]{color:#000;text-align:center!important}.button[_ngcontent-%COMP%]{border:none;outline:0;display:inline-block;padding:8px;color:#fff;background-color:#7b1fa2;text-align:center;cursor:pointer;width:100%;font-weight:700}.button[_ngcontent-%COMP%]:hover{background-color:#555}@media screen and (max-width:650px){.column[_ngcontent-%COMP%]{width:100%;display:block}}.header[_ngcontent-%COMP%]{font-family:Lucida Handwriting}']}),t})()}];var v=o("qAUw");let T=(()=>{class t{}return t.\u0275fac=function(n){return new(n||t)},t.\u0275mod=c.Mb({type:t}),t.\u0275inj=c.Lb({imports:[[r.c,v.a,a.a,e.g.forChild(f)]]}),t})()}}]);