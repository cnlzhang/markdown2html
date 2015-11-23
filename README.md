Markdown2HTML
=============

A powerful Markdown to HTML converter, powered by [Pegdown](https://github.com/vsch/pegdown)

Available in command line and GUI mode.

Licensed under the BSD license.


Requirements
------------

You need Java JVM 1.8 or newer installed on your machine.


Usage
-----

### GUI version ###

`java -jar Markdown2HTML.jar`

### Command line version ###

`java -jar Markdown2HTML.jar markdownFile [- header headerFile.html] [-footer footerFile.html] [-out [file.html]] [-d [directory]]`

#### Options for the command line version ####

- `markdownFile`: the text file which will be converted (compulsory field).
- `-header headerFile.html`: the path of an existing HTML header file.
Its content will be prepended to the converted `markdownFile` file.
- `-footer footerFile.html`: the path of an existing HTML footer file.
Its content will be appended to the converted `markdownFile` file.
- `-out`: enter this to specify that the program should create a new file
which contains the same name as the `markdownFile`, with the .html extension.
- `-out file.html`: enter this to specify the name of the converted file.
- `-d directory`: enter this to specify the directory of the converted files.

Markdown support
----------------
Refer to the [Pegdown project](https://github.com/vsch/pegdown) for details about 
supported markdown features. It basically supports full standard markdown
plus a bunch of extensions, including tables, definition lists etc, as found 
in [Github flavoured MarkDown](https://help.github.com/articles/github-flavored-markdown/).
Includes CSS style sheet for a GitHub-like look.

GUI Screenshot
--------------

<img src="http://nilhcem.github.com/screenshots/markdown2html.png" width="814" height="564" />


Versions
--------
Latest version: 1.1-SNAPSHOT
See [change log](CHANGES.md)

Building it
-----------

`mvn package`, that's all!
