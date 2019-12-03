---
title: "Java: Threads"
excerpt: "A thread is not an object, it's a series of executed instructions zipping thru method calls."
header:
  teaser: "assets/image/markup-syntax-highlighting-teaser.jpg"
tags: 
  - code
  - threads
sidebar:
  nav: java  
---


### Overview

A thread is not an object, it's a series of executed instructions zipping thru method calls. Imagine multiple CPUs and each one running code in your program (same data space) at the same time like ants crawling all over a code printout. Java and the operating system take care of making one or a few CPUs handle many threads. Here is the way I think of two threads executing methods foo() and bar():

Threads are useful because:

- support concurrent operation. HTTP server. crontab system (restarts managers, does stats, etc...).
- better system response. Add forum message; must add to search engine, email monitors.
- exploit parallelism. dual server: supports simultaneous execution
- Threads often permit simple programs. Sequential loop doing bits of this and that, but that is a scheduler. Don't have to check all the time for user events.

### Launching threads

1. Create class implementing Runnable.
2. Define public void run() method. Thread dies when it exits.
3. Elsewhere, create instance of class, a.
4. Create new Thread(a), t, attached to a.
5. t.start().

Do Jabber (like log server). Show lines of execution with interleaving.

```java
class Jabber implements Runnable {
  String str;
  public Jabber(String s) { str = s; }
  public void run() { // thread dies when finished
    while (true) {
      System.out.print(str);
      System.out.println();
    }
  }
}
```

Can use via:

```java
class JabberTest {

  public static void main(String[] args) {
    Jabber j = new Jabber("University of San Francisco");
    Jabber k = new Jabber("Computer Science 601");
    Thread t = new Thread(j);
    Thread u = new Thread(k);
    t.start();
    u.start();
  }
}
```
Should intermix not sync per line.


### Thread control

- join() wait for thread to finish

```java

Computation c = new Computation(34);
Thread t = new Thread(c);
t.start();
t.join();
System.out.println("done");
```

- sleep(int n) sleep for n ms (keep locks)
- interrupt() send signal to interrupt a sleeping or waiting thread
- yield suggest another can run

Here is a modified Jabber that forces a yield every 5 prints:

```java
public class Jabber implements Runnable {
    String str;
    int i = 0;
    public Jabber(String s) { str = s; }
    public void run() {
        while (true) {
            i++;
            if ( i%5==0 ) {
                Thread.yield();
            }
            System.out.print(str);
            System.out.println();
        }
    }
}
```

Auto yields when blocked on IO.

Here is the thread lifecycle:

<figure>
  <img src="{{ '/assets/images/javathreadlifecycle.jpg' | absolute_url }}" alt="Java">
</figure>


### Synchronization, Interference

## Shared resource example

"critical section": Trains on same track (semaphore term comes from this).

jGuru server pages: JSP share a single page object. Threads were stepping on the dynamic computations and output yielding bizarre stuff like the user name of one page would appear on somebody elses. I elected to have each page ref generate an object. GC can handle this pretty well. Plus I use a cache to avoid unnecessary computation.

## Race condition example

Bank teller issue; get a student to be other teller. Ask them to look at board where you have $100 in account. Boss goes to other teller, you go to me as teller. Both want to add 5$. Race condition. No matter what, it's wrong value. "test and set" operations must be synchronized. Then note that if you record changes not new value: $100, +5$, +5% then it's ok. No test and set. So, sync reqts depend on what you are doing. Looks like

```java
class Account {
  float balance = 0.0;
  public void deposit(float value) {
    balance = balance + value;
  }
}
```
## Monitors
Java's thread model based upon monitors: chunks of data accessed only thru set of mutually exclusive accessor routines. We can this an object:

```java
class Data {
      // ... elements ...
      public synchronized void insert(Object o) {...}
      public synchronized void delete(String key) {...}
      public synchronized void read(String key) {...}
}
```
A synchronized method acquires a lock on the object (not the class).

Protects methods exec not data.

What happens when another thread interrupts and calls deposit? Solution:

```java
class Account {
  float balance = 0.0;
  public synchronized void deposit(float value) {
    balance = balance + value;
  }
}
```java
Like a "force field" around object.

´Note:´ Can lock statements too with synchronized (object) statement.

´Note:´ Class methods can be synchronized also:

```java
class HPLaser {
  private static Device dev = ...;
  public static synchronized void print(String s) {...}
}
```
´Note:´ Assignments are atomic minus long and double.

´Note:´ local variables cannot be shared between threads so can't interfere.

Using threads to prevent data collisions!
Imagine that you have a complicated application with many, many classes. The various methods can trap errors that should be reported to the user. Easy enough, just build an ErrorManager with a static method called error():

```java
public class ErrorManager {
    public static void error(String msg) {
        System.err.println(msg);
    }
}
```
In this way, anybody can call ErrorManager.error() without having to pass around an object pointer to an instance of the error manager.

The problem is that there can be only one error manager because you are accessing a static method. Imagine you have a GUI where each window can operate independently. You want errors to go to different windows depending on the window, right? Are you stuck creating an instance of ErrorManager and passing it around to every object in your entire application just so each window can send errors to the proper place?

It turns out, you do not. Typically each GUI window's functionality will execute in a different thread so all you have to do is register with the ErrorManager where each thread should send errors and then call ErrorManager.error() as before!

```java
...
protected Map threadToOutputMap = new HashMap();

public static void setOutputStream(OutputStream os) {
    threadToOutputMap.put(Thread.currentThread(), os);
}

public static OutputStream getOutputStream() {
    OutputStream os = (OutputStream)
        threadToOutputMap.get(Thread.currentThread());
    if ( os!=null ) {
        return os;
    }
    return System.err;
}

public static void error(String msg) {
    getOutputStream().println(msg);
}
```

Now, when you say in thread 1:

```java
OutputStream window1ErrorStream = ...;
ErrorManager.setOutputStream(window1ErrorStream);
ErrorManager.error("bad input syntax");
```
and in thread 2:

```java

OutputStream window2ErrorStream = ...;
ErrorManager.setOutputStream(window2ErrorStream);
ErrorManager.error("bad input syntax");
```
then the errors will go to two different error streams depending on which thread issues the error.

In summary: multi-threaded applications normally cause contention and race conditions for shared data. In this case, we've used the very idea of different threads to keep data (error messages in our case) separate!

## Conditional synchronization and inter-thread communication

Want

```java
await (condition) do statement;
Have wait() and notifyAll().
```
Producer "/" consumer model such as blocking on I/O:


```java
/** Extend Queue to make threads block until remove has
 *  data.
 */
class BlockingQueue {
   int n = 0;
   Queue data = ...;
   public synchronized Object remove() {
      // wait until there is something to read
      while (n==0) this.wait();
      // we have the lock and state we're seeking
      n--;
      // return data element from queue
    }
    public synchronized void write(Object o) {
      n++;
      // add data to queue
      // have data.  tell any waiting threads to wake up
      notifyAll();
    }
}
```

Why is remove synchronized? It's destructive; must be critical section.

Why write synchronized? Critical section.

Here is an implementation of a 1-element queue:

```java
/** Simple queue that holds single value */
class BlockingQueue {
    int n = 0;
    Object data = null;
    public synchronized Object remove() {
        // wait until there is something to read
        try {
            while (n==0) wait();
        }
        catch (InterruptedException ie) {
            System.err.println("heh, who woke me up too soon?");
        }
        // we have the lock and state we're seeking; remove, return element
        Object o = this.data;
        this.data = null; // kill the old data
        n--;
        return o;
    }

    public synchronized void write(Object o) {
        n++;
        // add data to queue
        data = o;
        // have data.  tell any waiting threads to wake up 
        notifyAll();
    }
}
```
Here is a main() that tests the queue:

```java
class BlockingQueueTest {
    static class Producer implements Runnable {
        public void run() {
            q.write("hello");
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            System.out.println("data is "+q.remove());
        }
    }

    static BlockingQueue q;
    public static void main(String[] args) throws Exception {
        q = new BlockingQueue();
        new Thread(new Consumer()).start();
        Thread.sleep(2000);
        new Thread(new Producer()).start();
    }
}
```

Note that I try to consume first. It will wait for 2 seconds (2000 ms) before the producer starts up and adds the element.

`Barrier wait example.` t.join() allows us to wait until t has finished, but what about having n threads wait at a barrier like this?



For example, you might want to queue n people for each bus.

Want to code like this:

```java
class ParallelComputation implements Runnable {
    public void run() {
        // DO SOME COMPUTATION
  // now wait for others to finish
        try {
            Main.barrier.waitForRelease();
        }
        catch(InterruptedException e) {}
    }
}

public class Main {
    public static Barrier barrier = new Barrier(3);
    public static void main(String[] args) {
        new Thread(new ParallelComputation()).start();
        new Thread(new ParallelComputation()).start();
  // if you comment this one out, program hangs!
        new Thread(new ParallelComputation()).start();
    }
}
```
and this implementation

```java
/**A very simple barrier wait.  Once a thread has requested a
 * wait on the barrier with waitForRelease, it cannot fool the
 * barrier into releasing by "hitting" the barrier multiple times--
 * the thread is blocked on the wait().
 */
public class Barrier {
    protected int threshold;
    protected int count = 0;

    public Barrier(int t) {
        threshold = t;
    }

    public void reset() {
        count = 0;
    }
    
    public synchronized void waitForRelease()
        throws InterruptedException
    {
        count++;
        // The final thread to reach barrier resets barrier and
        // releases all threads
        if ( count==threshold ) {
            // notify blocked threads that threshold has been reached
            action(); // perform the requested operation
            notifyAll();
        }
        else while ( count<threshold ) {
            wait();
        }
    }

    /** What to do when the barrier is reached */
    public void action() {
        System.out.println("done");
    }
}
```

### Starvation
A thread with higher priority preempts your thread, never allowing it any CPU time. In Java, the thread with the highest priority is running, implying that any thread at a lower priority is starved unless the higher priority thread blocks or waits.

### Deadlock
Unsatisfied wait condition. Use HTTP project as an analogy (server must consume all headers from browser after the GET before sending result back or browser may hang. It's not looking for the response yet as it's waiting for the server to read all the headers).

Or, I'm waiting on you and you're waiting on me. Jim was waiting on me to tell him when I finished something and I was waiting on him.

Dining philosophers: think and eat. Get 2 students and some knives or chopsticks from the cafeteria. Must have two to eat but only one in front of you. Everybody grabs to the right and then waits for stick on left. Deadlock. If not available, wait until it is then eat. Get a cookie for student volunteers. One possibly solution is to have one philospher as a nonconformist: grabs left first. If they are greedy they starve. Could also have the grabbing of two sticks be synchronized so at least the first n-1 guys guy will finish.

## Avoidance
No universal solution. Redflags:

- watch out when x<->y and access methods are synchronized.
- watch out for unsatisfied conditions.
- watch out for lots of threads competing for limited resources.
