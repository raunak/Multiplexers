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
package com.ec;

import com.ec.Evolution.EvolutionType;

/**
 * Depending on the parameter passed to <code>Evolution</code>, <code>Main</code> executes the corresponding problem.
 * 
 * @author raunak
 * @version 1.0
 */
public class Main {

	public static void main(String args[]) {
		Evolution evolution = new Evolution(EvolutionType.MUX11, 10000);
		evolution.evolve();
	}
}