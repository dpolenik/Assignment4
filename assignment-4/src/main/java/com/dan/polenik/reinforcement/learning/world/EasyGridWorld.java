package com.dan.polenik.reinforcement.learning.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dan.polenik.reinforcement.learning.world.properties.RlProperties;
import com.dan.polenik.reinforcement.learning.world.util.AnalysisAggregator;
import com.dan.polenik.reinforcement.learning.world.util.AnalysisRunner;
import com.dan.polenik.reinforcement.learning.world.util.BasicRewardFunction;
import com.dan.polenik.reinforcement.learning.world.util.BasicTerminalFunction;
import com.dan.polenik.reinforcement.learning.world.util.MapPrinter;

import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.RewardFunction;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.singleagent.explorer.VisualExplorer;
import burlap.oomdp.visualizer.Visualizer;

@Component
public class EasyGridWorld {
	@Autowired
	RlProperties rlProperties;
	// These are some boolean variables that affect what will actually get executed
	private static boolean visualizeInitialGridWorld = true; // Loads a GUI with the agent, walls, and goal
	// showValueIterationPolicyMap, showPolicyIterationPolicyMap, and
	// showQLearningPolicyMap will open a GUI
	// you can use to visualize the policy maps. Consider only having one variable
	// set to true at a time
	// since the pop-up window does not indicate what algorithm was used to generate
	// the map.
	private static boolean showValueIterationPolicyMap = true;
	private static boolean showPolicyIterationPolicyMap = true;
	private static boolean showQLearningPolicyMap = true;

	private static Integer MAX_ITERATIONS = 100;
	private static Integer NUM_INTERVALS = 100;

	protected static int[][] userMap = new int[][] { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 },
			{ 1, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, };
	BasicGridWorld gen;
	Domain domain;
	State initialState;
	RewardFunction rf;
	TerminalFunction tf;
	SimulatedEnvironment env;
	AnalysisRunner runner;


	public void runEasyWorld() {
		runner = new AnalysisRunner(MAX_ITERATIONS, NUM_INTERVALS);
		// convert to BURLAP indexing
		int[][] map = MapPrinter.mapToMatrix(userMap);
		int maxX = map.length - 1;
		int maxY = map[0].length - 1;
		gen = new BasicGridWorld(map, maxX, maxY); // 0 index map is 11X11
		domain = gen.generateDomain();
		initialState = BasicGridWorld.getExampleState(domain);
		rf = new BasicRewardFunction(maxX, maxY); // Goal is at the top right grid
		tf = new BasicTerminalFunction(maxX, maxY); // Goal is at the top right grid
		env = new SimulatedEnvironment(domain, rf, tf, initialState);
		if (rlProperties.isPolicyIteration()) {
			runner.runPolicyIteration(gen, domain, initialState, rf, tf, showPolicyIterationPolicyMap);
		}
		if (rlProperties.isValueIteration()) {
			runner.runValueIteration(gen, domain, initialState, rf, tf, showValueIterationPolicyMap);
		}
		if (rlProperties.isqLearning()) {
			runner.runQLearning(gen, domain, initialState, rf, tf, env, showQLearningPolicyMap);
		}
		printAnalysis();
	}

	private void printAnalysis() {
		System.out.println("/////Easy Grid World Analysis/////\n");
		if (visualizeInitialGridWorld) {
			visualizeInitialGridWorld(domain, gen, env);
		}
		AnalysisAggregator.printAggregateAnalysis();
	}

	private void visualizeInitialGridWorld(Domain domain, BasicGridWorld gen, SimulatedEnvironment env) {
		Visualizer v = gen.getVisualizer();
		VisualExplorer exp = new VisualExplorer(domain, env, v);

		exp.addKeyAction("w", BasicGridWorld.ACTIONNORTH);
		exp.addKeyAction("s", BasicGridWorld.ACTIONSOUTH);
		exp.addKeyAction("d", BasicGridWorld.ACTIONEAST);
		exp.addKeyAction("a", BasicGridWorld.ACTIONWEST);

		exp.setTitle("Easy Grid World");
		exp.initGUI();

	}
}
