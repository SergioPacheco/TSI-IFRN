---
title: "Javascript: Tricks"
header:
  teaser: "assets/images/tricks.png"
tags: 
  - code
  - javascript
---


### Hacks: Introduction

The Web contains a fantastic array of services, applications, and interactive experiences. You can shop online, do research, check your email, and interact with other people on weblogs, message boards, and discussion forums. What do these all have in common? Forms.
Ask any web architect, and she'll tell you that web forms are woefully inadequate. At least two independent efforts are underway to radically overhaul the underlying technology of web forms. One of them might catch on; heck, the Web is big enough that both of them may catch on. But until then, we're stuck with the simple <form> element, a few <input> fields, and a Submit button.
Or are we?

```javascript
	// ==UserScript==
	// @name		  Display Form Action
	// @namespace	  http://diveintomark.org/projects/greasemonkey/
	// @description	  display form submission URL as tooltip of submit button
	// @include		  *
	// ==/UserScript==

	for (var i = document.forms.length - 1; i >= 0; i--) {
		var elmForm = document.forms[i];
		var snapSubmit = document.evaluate("//input[@type='submit']",
			elmForm, null, XPathResult.UNORDERED_NODE_SNAPSHOT_TYPE, null);
		for (var j = snapSubmit.snapshotLength - 1; j >= 0; j--) {
			var elmSubmit = snapSubmit.snapshotItem(j);
			elmSubmit.title = (elmForm.method.toUpperCase() || 'GET') +
				' ' + elmForm.action;
		}
	}

´´´
    .-.                             _   _                             .-.
   /   \           .-.             ((___))             .-.           /   \
  /.ooM \         /   \       .-.  [ x x ]  .-.       /   \         /.ooM \
-/-------\-------/-----\-----/---\--\   /--/---\-----/-----\-------/-------\-
/lucky  13\     /       \   /     `-(' ')-'     \   /       \     /lucky  13\
           \   /         `-'         (U)         `-'         \   /
            `-'                                               `-'