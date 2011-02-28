/* 
 * Copyright (c) 2011 Raunak Gupta; Kamil Olesiejuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.ec.generator;

import java.util.Random;

import com.ec.Individual;
import com.ec.node.Node;

/**
 * @version 1.0
 */
public abstract class Generator{
	
	/** Default probablity for stoping grow tree*/
	protected static float growStopProbability = 0.25f;
	
	/** A static instance of <code>Random</code>*/
	protected static Random random = new Random();
	
	public abstract Node getRandomTerminal(Node root);
	public abstract Node fullTree(int depth);
	public abstract Node growTree(int depth);
	public abstract Individual fitness(Node node);
	
}
