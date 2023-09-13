# Recompilation issue repro

Please note: you'll see that there quite a bit of seemingly-dead code (unneeded requires, var definitions, etc).
I've already tried to shrink the repro as much as possible, as far as I can tell all the code that is in here is necessary for the repro to work.

## Steps

1. Clone this repo
2. `npm i`
3. `npx shadow-cljs watch app`
4. Observe that the compialtion succeeds ("Build completed. ...")
5. Open a second terminal
6. `touch module0.cljs` (also works with module1, module2)
7. Observe that the compilation fails with `Use of undeclared Var module2/My-record`
8. `touch core.cljs` (also works with module3)

## Full transcript

```bash
[09/13 22:46:17] user@host
$ npx shadow-cljs watch app &              
[1] 1782685
[09/13 22:46:21] user@host
$ shadow-cljs - config: /home/simon/Projects/2023/recompilation-issue-repro/shadow-cljs.edn
shadow-cljs - server version: 2.25.4 running at http://localhost:9630
shadow-cljs - nREPL server started on port 43487
shadow-cljs - watching build :app
[:app] Configuring build.
[:app] Compiling ...
[:app] Build completed. (123 files, 0 compiled, 0 warnings, 1.84s)

[09/13 22:46:30] user@host
$ touch src/main/module0.cljs 
[09/13 22:46:44] user@host
$ [:app] Compiling ...
[:app] Build completed. (123 files, 3 compiled, 1 warnings, 0.10s)

------ WARNING #1 - :undeclared-var --------------------------------------------
 File: /home/simon/Projects/2023/recompilation-issue-repro/src/main/core.cljs:7:1
--------------------------------------------------------------------------------
   4 |             [module3]))
   5 | 
   6 | 
   7 | (module0/method-1 (module3/f2))
-------^------------------------------------------------------------------------
 Use of undeclared Var module2/My-record
--------------------------------------------------------------------------------

[09/13 22:46:47] user@host
$ touch src/main/module1.cljs 
[09/13 22:46:50] user@host
$ [:app] Compiling ...
[:app] Build completed. (123 files, 3 compiled, 1 warnings, 0.07s)

------ WARNING #1 - :undeclared-var --------------------------------------------
 File: /home/simon/Projects/2023/recompilation-issue-repro/src/main/core.cljs:7:1
--------------------------------------------------------------------------------
   4 |             [module3]))
   5 | 
   6 | 
   7 | (module0/method-1 (module3/f2))
-------^------------------------------------------------------------------------
 Use of undeclared Var module2/My-record
--------------------------------------------------------------------------------
                           
[09/13 22:46:55] user@host
$ touch src/main/module2.cljs 
[09/13 22:46:58] user@host
$ [:app] Compiling ...
[:app] Build completed. (123 files, 2 compiled, 1 warnings, 0.06s)

------ WARNING #1 - :undeclared-var --------------------------------------------
 File: /home/simon/Projects/2023/recompilation-issue-repro/src/main/core.cljs:7:1
--------------------------------------------------------------------------------
   4 |             [module3]))
   5 | 
   6 | 
   7 | (module0/method-1 (module3/f2))
-------^------------------------------------------------------------------------
 Use of undeclared Var module2/My-record
--------------------------------------------------------------------------------
                           
[09/13 22:47:02] user@host
$ touch src/main/module3.cljs
[09/13 22:47:06] user@host
$ [:app] Compiling ...
[:app] Build completed. (123 files, 2 compiled, 0 warnings, 0.04s)
                           
[09/13 22:47:08] user@host
$ touch src/main/core.cljs   
[09/13 22:47:12] user@host
$ [:app] Compiling ...
[:app] Build completed. (123 files, 1 compiled, 0 warnings, 0.04s)
```
