package org.antlr.intellij.plugin;

import com.intellij.find.impl.HelpID;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.impl.search.ThrowSearchUtil;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import org.antlr.intellij.plugin.adaptors.ANTLRUtils;
import org.antlr.intellij.plugin.adaptors.LexerAdaptor;
import org.antlr.intellij.plugin.parser.ANTLRv4Lexer;
import org.antlr.intellij.plugin.parser.ANTLRv4TokenTypes;
import org.antlr.intellij.plugin.psi.LexerRuleRefNode;
import org.antlr.intellij.plugin.psi.ParserRuleRefNode;
import org.antlr.intellij.plugin.psi.ParserRuleSpecNode;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ANTLRv4FindUsagesProvider implements FindUsagesProvider {
	@Override
	public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
		return true;
//		return psiElement instanceof PsiNamedElement;
	}

	@Nullable
	@Override
	public WordsScanner getWordsScanner() {
		return null; // seems ok as JavaFindUsagesProvider does same thing
//		System.out.println("getWordsScanner()");
//		final ANTLRv4Lexer lexer = new ANTLRv4Lexer(null);
//
//		LexerATNSimulator sim =
//			ANTLRUtils.getLexerATNSimulator(lexer, ANTLRv4Lexer._ATN, lexer.getInterpreter().decisionToDFA,
//											lexer.getInterpreter().getSharedContextCache());
//		lexer.setInterpreter(sim);
//		WordsScanner scanner =
//			new DefaultWordsScanner(new LexerAdaptor(lexer),
//									TokenSet.create(ANTLRv4TokenTypes.RULE_REF,
//													ANTLRv4TokenTypes.TOKEN_REF),
//									ANTLRv4TokenTypes.COMMENTS,
//									TokenSet.create(ANTLRv4TokenTypes.STRING_LITERAL)
//			);
//		return scanner;
	}

	@Nullable
	@Override
	public String getHelpId(@NotNull PsiElement element) {
		return HelpID.FIND_OTHER_USAGES;
	}

	@NotNull
	@Override
	public String getType(@NotNull PsiElement element) {
		return "rulellll";
	}

	@NotNull
	@Override
	public String getDescriptiveName(@NotNull PsiElement element) {
		PsiElement rule = PsiTreeUtil.findChildOfAnyType(element,
														 new Class[]{LexerRuleRefNode.class, ParserRuleRefNode.class});
		if ( rule!=null ) return rule.getText();
		return "n/a";
	}

	@NotNull
	@Override
	public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
		return getDescriptiveName(element);
	}
}
