---
date: <% tp.date.now("YYYY-MM-DD") %>
course: <% tp.file.folder(true).split("/").pop() %>
topic: <% tp.file.title %>
tags: [lecture]
---

<% tp.file.cursor() %>