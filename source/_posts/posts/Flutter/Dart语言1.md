---
author: "lei"
date: "2019-03-05"
title: Dart语言1
tags: "Dart,Flutter"
categories: "Flutter"
cover: /imagers/image-20221029043119569.png
top_img: /imagers/image-20221029043119569.png
---

##### -学习笔记

- 1.变量声明

  - var:可以接收任何类型的变量，var变量一旦赋值，类型便会确定，则不能再改变其类型，

              var t;
              t = "hi world";
              // 下面代码在dart中会报错，因为变量t的类型已经确定为String，
              // 类型一旦确定后则不能再更改其类型。
              t = 1000;
  - dynamic和Object
    - Object 是Dart所有对象的根基类(包括Function和Null)，dynamic与var一样都是关键词,声明的变量可以赋值任意对象。
               而dynamic与Object相同之处在于,他们声明的变量可以在后期改变赋值类型。
    - dynamic与Object不同的是,dynamic声明的对象编译器会提供所有可能的组合, 而Object声明的对象只能使用Object的属性与方法

                   dynamic a;
                   Object b;
                   main() {
                       a = "";
                       b = "";
                       printLengths();
                   }   
                      
                   printLengths() {
                       // no warning
                       print(a.length);
                       // warning:
                       // The getter 'length' is not defined for the class 'Object'
                       print(b.length);
                   }
  - final和const
    - 一个 final 变量只能被设置一次
    - const 变量是一个编译时常量
    - final变量在第一次使用时被初始化。
    - 被final或者const修饰的变量，变量类型可以省略

- 2.函数
  - 函数声明

        bool isNoble(int atomicNumber) {
          return _nobleGases[atomicNumber] != null;
        }       
  - Dart函数声明如果没有显式声明返回值类型时会默认当做dynamic处理，注意，函数返回值没有类型推断：

          typedef bool CALLBACK();
      
        //不指定返回类型，此时默认为dynamic，不是bool
        isNoble(int atomicNumber) {
          return _nobleGases[atomicNumber] != null;
        }
      
        void test(CALLBACK cb){
           print(cb()); 
        }
        //报错，isNoble不是bool类型
        test(isNoble);
  - 对于只包含一个表达式的函数，可以使用简写语法

        bool isNoble (int atomicNumber)=> _nobleGases [ atomicNumber ] ！= null ;
  - 函数作为变量

        var say = (str){
          print(str);
        };
        say("hi world");
  - 函数作为参数传递

        void execute(var callback) {
            callback();
        }
        execute(() => print("xxx")) 
  - 可选的位置参数

        //包装一组函数参数，用[]标记为可选的位置参数，并放在参数列表的最后面：
        String say(String from, String msg, [String device]) {
          var result = '$from says $msg';
          if (device != null) {
            result = '$result with a $device';
          }
          return result;
        }
        //调用
        say('Bob', 'Howdy'); //结果是： Bob says Howdy
        say('Bob', 'Howdy', 'smoke signal'); //结果是：Bob says Howdy with a smoke signal
  - 可选的命名参数

        //定义函数时，使用{param1, param2, …}，放在参数列表的最后面，用于指定命名参数。例如：      
        //设置[bold]和[hidden]标志
        void enableFlags({bool bold, bool hidden}) {
            // ... 
        }
        //调用函数时，可以使用指定命名参数。例如：paramName: value
        enableFlags(bold: true, hidden: false);

- 3.异步支持
  - Future
    - Future与JavaScript中的Promise非常相似，表示一个异步操作的最终完成（或失败）及其结果值的表示
    - 一个Future只会对应一个结果，要么成功，要么失败
    - Future 的所有API的返回值仍然是一个Future对象
  - Future.then

        Future.delayed(new Duration(seconds: 2),(){
           return "hi world!";
        }).then((data){
           print(data);
        });
  - Future.catchError

        Future.delayed(new Duration(seconds: 2),(){
           //return "hi world!";
           throw AssertionError("Error");  
        }).then((data){
           //执行成功会走到这里  
           print("success");
        }).catchError((e){
           //执行失败会走到这里  
           print(e);
        });
    - onError

            Future.delayed(new Duration(seconds: 2), () {
                //return "hi world!";
                throw AssertionError("Error");
            }).then((data) {
                print("success");
            }, onError: (e) {
                print(e);
            });
  - Future.whenComplete

        Future.delayed(new Duration(seconds: 2),(){
           //return "hi world!";
           throw AssertionError("Error");
        }).then((data){
           //执行成功会走到这里 
           print(data);
        }).catchError((e){
           //执行失败会走到这里   
           print(e);
        }).whenComplete((){
           //无论成功或失败都会走到这里
        });
  - Future.wait
    - 只有数组中所有Future都执行成功后，才会触发then的成功回调，只要有一个Future执行失败，就会触发错误回调

           Future.wait([
            // 2秒后返回结果  
            Future.delayed(new Duration(seconds: 2), () {
              return "hello";
            }),
            // 4秒后返回结果  
            Future.delayed(new Duration(seconds: 4), () {
              return " world";
            })
          ]).then((results){
            print(results[0]+results[1]);
          }).catchError((e){
            print(e);
          });
  - Async/await
    - Dart中的async/await 和JavaScript中的async/await功能和用法是一模一样的，如果你已经了解JavaScript中的async/await的用法
    - 回调地狱(Callback Hell)
      - 使用Future消除Callback Hell

            login("alice","******").then((id){
                  return getUserInfo(id);
            }).then((userInfo){
                return saveUserInfo(userInfo);
            }).then((e){
               //执行接下来的操作 
            }).catchError((e){
              //错误处理  
              print(e);
            });
      - 使用async/await消除callback hell

            task() async {
               try{
                String id = await login("alice","******");
                String userInfo = await getUserInfo(id);
                await saveUserInfo(userInfo);
                //执行接下来的操作   
               } catch(e){
                //错误处理   
                print(e);   
               }  
            }

- 4.Stream
  - 接收多个异步操作的结果（成功或失败）

        Stream.fromFutures([
          // 1秒后返回结果
          Future.delayed(new Duration(seconds: 1), () {
            return "hello 1";
          }),
          // 抛出一个异常
          Future.delayed(new Duration(seconds: 2),(){
            throw AssertionError("Error");
          }),
          // 3秒后返回结果
          Future.delayed(new Duration(seconds: 3), () {
            return "hello 3";
          })
        ]).listen((data){
           print(data);
        }, onError: (e){
           print(e.message);
        },onDone: (){
        
        });