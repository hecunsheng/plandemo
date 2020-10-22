package run.star.plan.filter;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;

/**
 * @author hecs
 * @date 2020-10-12 16:04
 */
public class JavaDocUse {
    private static RootDoc rootDoc;

    public static class Doclet {
        public static boolean start(RootDoc rootDoc) {
            JavaDocUse.rootDoc = rootDoc;
            return true;
        }
    }

    /**
     * 显示DocRoot中的基本信息
     */
    public static void show() {
        ClassDoc[] classes = rootDoc.classes();
        for (ClassDoc classDoc : classes) {
            MethodDoc[] methodDocs = classDoc.methods();
            for (MethodDoc methodDoc : methodDocs) {
                // 打印出方法上的注释
                System.out.println(classDoc.name() + "." + methodDoc.name() + "--->" + methodDoc.commentText().replace("\n", " "));
            }
        }
    }

    public static void main(String[] args) throws Exception {

//        String a = "cn.estudy.course.service.facade.CourseServiceProviderFacade";
//        Class clazz = Class.forName(a);
//        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
//        System.out.println(url.toURI());

        Main.execute(new String[]{"-doclet",
                Doclet.class.getName(),
                "-encoding", "utf-8", "-classpath",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/target/classes",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/order/service/facade/OrderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/order/service/facade/OrderPromotionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/order/service/facade/OrderServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/study/service/facade/StudyTaskPageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/study/service/facade/CreditRewardFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/LiveClassBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/LearnStatisticFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/inner/PeriodRelationPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/inner/LiveCoursePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/inner/MemberLessonAccessPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/LiveScreenshotNoteFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/CourseProdTaskAfterFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/VodCourseFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/CourseServiceProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/GraduationCertificateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/LectureFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/GiveCourseBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/course/service/facade/LessonEvaluateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/app/service/facade/AppVersionServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/app/service/facade/AppCalendarFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/app/service/facade/AppInfoFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/app/service/facade/AppBusinessRuleFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/back/service/facade/HomepageBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/comment/service/facade/CommentFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/comment/service/facade/CommentBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/fm/service/facade/FmFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/fm/service/facade/FmBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/point/service/facade/WidgetFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/point/service/facade/WidgetProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/point/service/facade/PointFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/member/service/facade/MemberFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/member/service/facade/MemberCourseFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/member/service/facade/FeedbackForHomeworkFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/message/service/facade/PhoneNotifyCallbackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/message/service/facade/PrivateChatFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/message/service/facade/PhoneNotifyProxyServicePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/message/service/facade/AppAndWechatMsgPushFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/coupon/service/facade/CouponBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/coupon/service/facade/CouponFrontFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/coupon/service/facade/CouponProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/ProductServiceProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/ProductFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/HomepageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/NewUserBenefitFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/CourseDetailTplBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/ProductDetailFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/ProductContextProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/PromotionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/product/service/facade/PromotionProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/wechat/service/facade/DailyQuestionForWechatFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/invite/facade/InviteActivityFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/invite/facade/RedEnvelopeFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/invite/facade/ActivityFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/CommonKeyValueFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/CityFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/BannedWordsFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/CommonFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/BannedWordManageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/VideoUploadFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/SpecialFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/BannerFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/LiveVideoFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/IconFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/ClassTypeFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/AreaSubjectVersionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/GameTypeFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/FeedbackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/HomepageResourceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/common/service/facade/DataDictionaryFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/vod/service/facade/VodPlayServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/vod/service/facade/MyVodProductFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/growthcamp/service/facade/ClockFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/cms/service/facade/FreeCourseProductFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/b2c/service/facade/AuditionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/b2c/service/facade/GroupPurchaseFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/AssistConnectMicrophoneFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveGroupFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/TimingLiveFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveGameFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveClassManagerFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveassistStatisticsFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveGiftFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/CompetitionPointFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveCheckFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveClassFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/ImServerServicePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/MemberCoursePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/SchoolFilterPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/VideoDefinitionPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/DataDictionaryPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/WrongQuestionPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveRankingPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveNotifyPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/TxMessagePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveVideoPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveLessonPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/PaperResultPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LivePaperQuestionPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveClassMemberPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveClassPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/AliYunVodPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveLessonResourcePrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/LiveVideoPointPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/inner/ProductPrivateFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/NotifyServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/AssistManagerFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveRankingFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/AssistLiveFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveLessonFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/ShutUpFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveCourseFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveVerifyCodeFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveTextFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/AssistMessageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LessonNoteFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveTalkMaxFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/CourseTaskFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/VodFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/CourseTaskAfterFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveAnswerFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveCourseQueryFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/CourseMemberCenterFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/StudyDataServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/GamePointFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveAssistRecoverFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/StudyTaskServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveStatisticInfosFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/NoticeCheckFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveClassProviderFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LearnRecordFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveJobFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveBasicInfoFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveStudentFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveCourseEditFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveCasterFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveWatchTestFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveConnectMicrophoneFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LiveInteractionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/live/service/facade/LessonActionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/xscproj/service/facade/XscAdminFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/xscproj/service/facade/XscFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/CourseStudyReportBackFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/LiveLessonSummaryFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/WeekReportServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/ClassEndReportServiceFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/RptUserFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/CourseStudyReportFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/report/service/facade/YearlyReportFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/exam/service/facade/ExamManageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/exam/service/facade/UserExamFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/exam/service/facade/ExamFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/exam/service/facade/ExamTestFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/DailyQuestionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/DailyQuestionManageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/PaperResultFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/RegistrationDiagnosisFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/HomeworkForAppFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/LivePaperQuestionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/HomeworkImageFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/QBankManagementFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/QuestionConvertFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/WrongQuestionFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/qbank/service/facade/LiveGroupPaperFacade.java",
                "/Users/hecs/kklgitworkspace/estudy/estudy-iface/src/main/java/cn/estudy/msgPush/service/facade/AppAndWechatInfoFacade.java"
        });
        show();
    }
}
