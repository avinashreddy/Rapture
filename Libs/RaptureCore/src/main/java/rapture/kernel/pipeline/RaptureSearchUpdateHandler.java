package rapture.kernel.pipeline;

import org.apache.log4j.Logger;

import rapture.common.RapturePipelineTask;
import rapture.common.impl.jackson.JacksonUtil;
import rapture.common.mime.MimeSearchUpdateObject;
import rapture.exchange.QueueHandler;
import rapture.kernel.Kernel;

public class RaptureSearchUpdateHandler implements QueueHandler {

    private static final Logger log = Logger.getLogger(RaptureSearchUpdateHandler.class);
 
    private final PipelineTaskStatusManager statusManager;

    public RaptureSearchUpdateHandler() {
        statusManager = new PipelineTaskStatusManager();
    }

    @Override
    public boolean handleMessage(String tag, String routing, String contentType, RapturePipelineTask task) {
        String content = task.getContent();
        log.info("Processing search update request"); //$NON-NLS-1$
        try {
            statusManager.startRunning(task);
            MimeSearchUpdateObject doc = JacksonUtil.objectFromJson(content, MimeSearchUpdateObject.class);
            
            switch(doc.getType()) {
            case CREATE:
                Kernel.getSearch().getTrusted().writeSearchEntry(doc.getRepo(), doc.getDoc());
                break;
            case DELETE:
            	Kernel.getSearch().getTrusted().deleteSearchEntry(doc.getRepo(), doc.getDoc().getDisplayName());
            	break;
            default:
            	log.error("Don't know how to process this search update");
            }
            // Call something in
            statusManager.finishRunningWithSuccess(task);

        } catch (Exception e) {
            log.error(String.format("Failed to process update %s", //$NON-NLS-1$
                    e.getMessage()));
            statusManager.finishRunningWithFailure(task);
        }
        return true;
    }

	

}
