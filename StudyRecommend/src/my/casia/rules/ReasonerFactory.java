package my.casia.rules;

/**
 * @purpose ���󴴽�����
 * @author zhaohongjie
 * 
 */
public class ReasonerFactory {

	public static IReasoner createFamilyReasoner() {
		IReasoner familyReasoner = new ReasonerImpl();
		return familyReasoner;
	}
}
