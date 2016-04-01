package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class FilterWebContent implements ICanFilterWebContent{
    String type = "null";
    String patternCompile = "null";
    ICanFilterWebContent next;

    public FilterWebContent() {
    }

    public ICanFilterWebContent getNext() {
        return next;
    }

    public void setNext(ICanFilterWebContent next) {
        this.next = next;
    }

    public void setPatternCompile(String patternCompile) {
        this.patternCompile = patternCompile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ICanFilterWebContent buildNext(ICanFilterWebContent _next) {
        ((FilterWebContent)_next).setNext(this);
        return _next;
    }

    @Override
    public ICanFilterWebContent getFilter(String _type) {
        if(_type.contains(this.getType()))
        {
            return this;
        } else {
            if(this.getNext() != null)
            {
                return this.getNext().getFilter(_type);
            } else {
                return new FilterWebContent();
            }
        }
    }

    @Override
    public ICanFilterWebContent next() {
        return this.next;
    }

    @Override
    public String getPatternCompile() {
        return this.patternCompile;
    }
}
