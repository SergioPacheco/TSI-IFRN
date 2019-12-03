---
title: "IA: Machine Learning"
header:
  teaser: "assets/images/max-weber.jpg"
categories:	
  - IA
tags:
  - ML
---

So, what is deep learning?

It’s a term that covers a particular approach to building and training neural networks. Neural networks have been around since the 1950s, and like nuclear fusion, they’ve been an incredibly promising laboratory idea whose practical deployment has been beset by constant delays. I’ll go into the details of how neural networks work a bit later, but for now you can think of them as decision-making black boxes. They take an array of numbers (that can represent pixels, audio waveforms, or words), run a series of functions on that array, and output one or more numbers as outputs. The outputs are usually a prediction of some properties you’re trying to guess from the input, for example whether or not an image is a picture of a cat.

Get O'Reilly's weekly data newsletter


The functions that are run inside the black box are controlled by the memory of the neural network, arrays of numbers known as weights that define how the inputs are combined and recombined to produce the results. Dealing with real-world problems like cat-detection requires very complex functions, which mean these arrays are very large, containing around 60 million numbers in the case of one of the recent computer vision networks. The biggest obstacle to using neural networks has been figuring out how to set all these massive arrays to values that will do a good job transforming the input signals into output predictions.

Training

One of the theoretical properties of neural networks that has kept researchers working on them is that they should be teachable. It’s pretty simple to show on a small scale how you can supply a series of example inputs and expected outputs, and go through a mechanical process to take the weights from initial random values to progressively better numbers that produce more accurate predictions (I’ll give a practical demonstration of that later). The problem has always been how to do the same thing on much more complex problems like speech recognition or computer vision with far larger numbers of weights.

That was the real breakthrough in the 2012 Imagenet paper sparking the current renaissance in neural networks. Alex Krizhevsky, Ilya Sutskever, and Geoff Hinton brought together a whole bunch of different ways of accelerating the learning process, including convolutional networks, clever use of GPUs, and some novel mathematical tricks like ReLU and dropout, and showed that in a few weeks they could train a very complex network to a level that outperformed conventional approaches to computer vision.

This isn’t an aberration, similar approaches have been used very successfully in natural language processing and speech recognition. This is the heart of deep learning — the new techniques that have been discovered that allow us to build and train neural networks to handle previously unsolved problems.

How is it different from other approaches?

With most machine learning, the hard part is identifying the features in the raw input data, for example SIFT or SURF in images. Deep learning removes that manual step, instead relying on the training process to discover the most useful patterns across the input examples. You still have to make choices about the internal layout of the networks before you start training, but the automatic feature discovery makes life a lot easier. In other ways, too, neural networks are more general than most other machine-learning techniques. I’ve successfully used the original Imagenet network to recognize classes of objects it was never trained on, and even do other image tasks like scene-type analysis. The same underlying techniques for architecting and training networks are useful across all kinds of natural data, from audio to seismic sensors or natural language. No other approach is nearly as flexible.