package com.dddbook.bank.exception;

import org.apache.commons.lang3.Validate;

public interface I18nException<T extends Exception & I18nException>  {
    T setMessageTemplate(String var1, String... var2);

    I18nException.MessageTemplate getMessageTemplate();

    public static class MessageTemplate {
        private transient String templateId;
        private transient String[] args;

        public MessageTemplate(String templateId, String... args) {
            this.templateId = (String) Validate.notNull(templateId);
            this.args = args;
        }

        public String getTemplateId() {
            return this.templateId;
        }

        public I18nException.MessageTemplate setTemplateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public String[] getArgs() {
            return this.args;
        }

        public I18nException.MessageTemplate setArgs(String[] args) {
            this.args = args;
            return this;
        }
    }
}
